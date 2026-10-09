package com.kiranacademy.hospital;

import java.util.List;

public interface PatientDAO {

    List<Patient> getPendingPatients();

    public boolean alreadyTransferred(int patientId);

    public void insertCriticalPatient(Patient patient);

    public void insertGeneralPatient(Patient patient);

    public void markProcessed(int patientId);
}