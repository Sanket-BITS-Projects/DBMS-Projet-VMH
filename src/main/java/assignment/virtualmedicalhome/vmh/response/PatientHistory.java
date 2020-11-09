package assignment.virtualmedicalhome.vmh.response;

import assignment.virtualmedicalhome.vmh.model.Appointment;
import assignment.virtualmedicalhome.vmh.model.Illness;
import assignment.virtualmedicalhome.vmh.model.Prescription;

public class PatientHistory {
    private Appointment appointment;
    private Illness illness;
    private Prescription prescription;

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Illness getIllness() {
        return illness;
    }

    public void setIllness(Illness illness) {
        this.illness = illness;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }
}
