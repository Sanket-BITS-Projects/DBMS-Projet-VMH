package assignment.virtualmedicalhome.vmh.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "appointment")
public class Appointment {

    private int id;
    private int patient;
    private int doctor;
    private int illness;
    private Date time;
    private int duration;
    private int doctorAccept;
    private int shareHistory;
    private Date timestamp;


    public Appointment() {

    }

    public Appointment(int id, int patient, int doctor, int illness, Date time, int duration, int doctorAccept, int shareHistory, Date timestamp) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.illness = illness;
        this.time = time;
        this.duration = duration;
        this.doctorAccept = doctorAccept;
        this.shareHistory = shareHistory;
        this.timestamp = timestamp;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "A_ID")
    public int getId() {
        return id;
    }

    public void setId(int aId) {
        this.id = aId;
    }

    @Basic
    @Column(name = "I_ID")
    public int getIllness() {
        return illness;
    }

    public void setIllness(int illness) {
        this.illness = illness;
    }

    @Basic
    @Column(name = "P_ID")
    public int getPatient() {
        return patient;
    }

    public void setPatient(int patient) {
        this.patient = patient;
    }

    @Basic
    @Column(name = "D_ID")
    public int getDoctor() {
        return doctor;
    }

    public void setDoctor(int doctor) {
        this.doctor = doctor;
    }

    @Basic
    @Column(name = "A_TIME")
    public Date getTime() {
        return time;
    }

    public void setTime(Date aTime) {
        this.time = aTime;
    }

    @Basic
    @Column(name = "DURATION")
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Basic
    @Column(name = "D_ACCEPT")
    public int getDoctorAccept() {
        return doctorAccept;
    }

    public void setDoctorAccept(int dAccept) {
        this.doctorAccept = dAccept;
    }

    @Basic
    @Column(name = "SHARE_HISTORY")
    public int getShareHistory() {
        return shareHistory;
    }

    public void setShareHistory(int shareHistory) {
        this.shareHistory = shareHistory;
    }

    @Basic
    @Column(name = "TIMESTAMP")
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment appointment = (Appointment) o;
        return id == appointment.id &&
                patient == appointment.patient &&
                doctor == appointment.doctor &&
                illness == appointment.illness &&
                time.equals(appointment.time) &&
                duration == appointment.duration &&
                doctorAccept == appointment.doctorAccept &&
                shareHistory == appointment.shareHistory &&
                timestamp.equals(appointment.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patient, doctor, illness, time, duration, doctorAccept, shareHistory, timestamp);
    }
}
