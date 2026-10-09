package com.kiranacademy.hospital;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDaoImpl implements PatientDAO {

	Connection con;

	public PatientDaoImpl(Connection con) {
		this.con = con;
	}

	@Override
	public List<Patient> getPendingPatients() {

		List<Patient> patients = new ArrayList<Patient>();

		String sql = "SELECT * FROM hospital_patient_intake " + "WHERE transfer_status = 'PENDING'";

		try {

			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Patient patient = new Patient();

				patient.setPatientId(rs.getInt("patient_id"));

				patient.setPatientName(rs.getString("patient_name"));

				patient.setAge(rs.getInt("age"));

				patient.setGender(rs.getString("gender"));

				patient.setDisease(rs.getString("disease"));

				patient.setAdmissionType(rs.getString("admission_type"));

				patient.setConditionStatus(rs.getString("condition_status"));

				patient.setTriageScore(rs.getInt("triage_score"));

				patient.setDoctorName(rs.getString("doctor_name"));

				patient.setAdmissionDate(rs.getDate("admission_date"));

				patient.setMobile(rs.getString("mobile"));

				patient.setTransferStatus(rs.getString("transfer_status"));

				patients.add(patient);
			}

		} catch (SQLException e) {

			System.out.println("Error getting patients: " + e);
		}

		return patients;
	}

	@Override
	public boolean alreadyTransferred(int patientId) {

		String sql = "SELECT source_patient_id " + "FROM critical_care_patients " + "WHERE source_patient_id = ?";

		try {

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, patientId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				return true;
			}

		} catch (SQLException e) {

			System.out.println("Critical table check error: " + e);
		}

		String sql2 = "SELECT source_patient_id " + "FROM general_care_patients " + "WHERE source_patient_id = ?";

		try {

			PreparedStatement ps = con.prepareStatement(sql2);

			ps.setInt(1, patientId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				return true;
			}

		} catch (SQLException e) {

			System.out.println("General table check error: " + e);
		}

		return false;
	}

	@Override
	public void insertCriticalPatient(Patient patient) {

	    String sql = "INSERT INTO critical_care_patients "
	               + "(source_patient_id, patient_name, age, disease, "
	               + "admission_type, condition_status, triage_score, doctor_name) "
	               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	    try {

	        PreparedStatement ps = con.prepareStatement(sql);

	        ps.setInt(1, patient.getPatientId());
	        ps.setString(2, patient.getPatientName());
	        ps.setInt(3, patient.getAge());
	        ps.setString(4, patient.getDisease());
	        ps.setString(5, patient.getAdmissionType());
	        ps.setString(6, patient.getConditionStatus());
	        ps.setInt(7, patient.getTriageScore());
	        ps.setString(8, patient.getDoctorName());

	        ps.executeUpdate();

	        System.out.println("Critical patient inserted.");

	    } catch (SQLException e) {

	        System.out.println(
	                "Critical insert error: " + e);
	    }
	}
	@Override
	public void insertGeneralPatient(Patient patient) {

	    String sql = "INSERT INTO general_care_patients "
	               + "(source_patient_id, patient_name, age, disease, "
	               + "admission_type, condition_status, triage_score, doctor_name) "
	               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	    try {

	        PreparedStatement ps = con.prepareStatement(sql);

	        ps.setInt(1, patient.getPatientId());
	        ps.setString(2, patient.getPatientName());
	        ps.setInt(3, patient.getAge());
	        ps.setString(4, patient.getDisease());
	        ps.setString(5, patient.getAdmissionType());
	        ps.setString(6, patient.getConditionStatus());
	        ps.setInt(7, patient.getTriageScore());
	        ps.setString(8, patient.getDoctorName());

	        ps.executeUpdate();

	        System.out.println("General patient inserted.");

	    } catch (SQLException e) {

	        System.out.println(
	                "General insert error: " + e);
	    }
	}
	@Override
	public void markProcessed(int patientId) {

		String sql = "UPDATE hospital_patient_intake " + "SET transfer_status = 'PROCESSED' " + "WHERE patient_id = ?";

		try {

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, patientId);

			ps.executeUpdate();

			System.out.println("Patient marked as processed.");

		} catch (SQLException e) {

			System.out.println("Status update error: " + e);
		}
	}
}