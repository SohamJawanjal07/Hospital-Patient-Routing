package com.kiranacademy.hospital;

import java.sql.Connection;
import java.util.List;

public class PatientRoutingService {

	Connection con;

	PatientDaoImpl patientDao;

	public PatientRoutingService() {

		con = DBConnection.getConnection();

		patientDao = new PatientDaoImpl(con);
	}

	public void processPatients() {

		try {

			if (con == null) {

				System.out.println("Database connection failed.");

				return;
			}

			con.setAutoCommit(false);

			List<Patient> patients = patientDao.getPendingPatients();

			ProcessingSummary summary = new ProcessingSummary();

			for (Patient patient : patients) {

				summary.increaseTotalPendingRecords();


				String result = PatientValidator.validatePatient(patient);

				if (!result.equals("VALID")) {

					System.out.println("Patient " + patient.getPatientId() + " -> FAILED -> " + result);

					summary.increaseValidationFailed();

					continue;
				}

				// Check duplicate

				if (patientDao.alreadyTransferred(patient.getPatientId())) {

					System.out.println("Patient " + patient.getPatientId() + " -> SKIPPED -> " + "Already processed");

					summary.increaseSkippedDuplicate();

					continue;
				}

				// Check Critical or General

				if (isCritical(patient)) {

					patientDao.insertCriticalPatient(patient);

					patientDao.markProcessed(patient.getPatientId());

					con.commit();

					System.out.println("Patient " + patient.getPatientId() + " -> CRITICAL CARE -> SUCCESS");

					summary.increaseCriticalCare();
					summary.increaseSuccessfullyProcessed();

				} else {

					patientDao.insertGeneralPatient(patient);

					patientDao.markProcessed(patient.getPatientId());

					con.commit();

					System.out.println("Patient " + patient.getPatientId() + " -> GENERAL CARE -> SUCCESS");

					summary.increaseGeneralCare();
					summary.increaseSuccessfullyProcessed();
				}
			}

			summary.displaySummary();

		} catch (Exception e) {

			System.out.println("Processing Error: " + e);

			try {

				if (con != null) {

					con.rollback();

					System.out.println("Transaction rolled back.");
				}

			} catch (Exception rollbackError) {

				System.out.println("Rollback Error: " + rollbackError);
			}

		} finally {

			DBConnection.closeConnection(con);
		}
	}

	public boolean isCritical(Patient patient) {

		if (patient.getConditionStatus().equals("Critical")) {

			return true;
		}

		if (patient.getAdmissionType().equals("Emergency") && patient.getTriageScore() >= 7) {

			return true;
		}

		if (patient.getConditionStatus().equals("Moderate") && patient.getTriageScore() >= 8) {

			return true;
		}

		return false;
	}
}