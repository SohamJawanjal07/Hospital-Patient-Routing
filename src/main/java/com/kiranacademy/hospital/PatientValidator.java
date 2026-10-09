package com.kiranacademy.hospital;

import java.sql.Date;

public class PatientValidator {

	public static String validatePatient(Patient patient) {

		if (patient.getPatientName() == null || patient.getPatientName().length() < 3) {

			return "Patient name is invalid";
		}

		if (patient.getAge() < 0 || patient.getAge() > 120) {

			return "Age is invalid";
		}

		if (!(patient.getGender().equals("Male") || patient.getGender().equals("Female")
				|| patient.getGender().equals("Other"))) {

			return "Gender is invalid";
		}

		if (patient.getDisease() == null || patient.getDisease().trim().isEmpty()) {

			return "Disease is empty";
		}

		if (!(patient.getAdmissionType().equals("Emergency") || patient.getAdmissionType().equals("Regular"))) {

			return "Admission type is invalid";
		}

		if (!(patient.getConditionStatus().equals("Critical") || patient.getConditionStatus().equals("Moderate")
				|| patient.getConditionStatus().equals("Stable"))) {

			return "Condition status is invalid";
		}

		if (patient.getTriageScore() < 1 || patient.getTriageScore() > 10) {

			return "Triage score is invalid";
		}

		if (patient.getDoctorName() == null || patient.getDoctorName().trim().isEmpty()) {

			return "Doctor name is empty";
		}

		if (patient.getAdmissionDate() == null) {

			return "Admission date is empty";
		}

		Date today = new Date(System.currentTimeMillis());

		if (patient.getAdmissionDate().after(today)) {

			return "Admission date cannot be future date";
		}

		if (patient.getMobile() == null || !patient.getMobile().matches("\\d{10}")) {

			return "Mobile number is invalid";
		}

		if (!patient.getTransferStatus().equals("PENDING")) {

			return "Patient is not pending";
		}

		return "VALID";
	}
}