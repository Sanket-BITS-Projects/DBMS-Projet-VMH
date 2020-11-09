package assignment.virtualmedicalhome.vmh.response;

import assignment.virtualmedicalhome.vmh.model.*;

public class DoctorAppointmentHistory {
    private Appointment appointment;
    private Person patient;
    private Person doctor;
    private DoctorCommission commission;
    private Prescription prescription;
    private Illness illness;

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Person getPatient() {
        return patient;
    }

    public void setPatient(Person patient) {
        this.patient = patient;
    }

    public Person getDoctor() {
        return doctor;
    }

    public void setDoctor(Person doctor) {
        this.doctor = doctor;
    }

    public DoctorCommission getCommission() {
        return commission;
    }

    public void setCommission(DoctorCommission commission) {
        this.commission = commission;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public Illness getIllness() {
        return illness;
    }

    public void setIllness(Illness illness) {
        this.illness = illness;
    }
}
