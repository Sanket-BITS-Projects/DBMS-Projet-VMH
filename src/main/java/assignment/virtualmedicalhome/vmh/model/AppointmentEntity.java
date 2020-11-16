package assignment.virtualmedicalhome.vmh.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "APPOINTMENT", schema = "PUBLIC", catalog = "VMHDB.DB")
public class AppointmentEntity {
    private int aId;
    private Date aDateTime;
    private int duration;
    private Date timestamp;
    private char doctorAccept;
    private int patientId;
    private int iId;
    private int doctorId;
    private PersonEntity patient;
    private IllnessEntity illnessByIId;
    private DoctorEntity doctor;
    private PrescriptionEntity prescriptionByAId;

    @Id
    @Column(name = "A_ID")
    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    @Basic
    @Column(name = "A_DATE_TIME")
    public Date getaDateTime() {
        return aDateTime;
    }

    public void setaDateTime(Date aDateTime) {
        this.aDateTime = aDateTime;
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
    @Column(name = "TIMESTAMP")
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Basic
    @Column(name = "DOCTOR_ACCEPT")
    public char getDoctorAccept() {
        return doctorAccept;
    }

    public void setDoctorAccept(char doctorAccept) {
        this.doctorAccept = doctorAccept;
    }

    @Basic
    @Column(name = "PATIENT_ID")
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    @Basic
    @Column(name = "I_ID")
    public int getiId() {
        return iId;
    }

    public void setiId(int iId) {
        this.iId = iId;
    }

    @Basic
    @Column(name = "DOCTOR_ID")
    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppointmentEntity that = (AppointmentEntity) o;
        return Objects.equals(aId, that.aId) &&
                Objects.equals(aDateTime, that.aDateTime) &&
                Objects.equals(duration, that.duration) &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(doctorAccept, that.doctorAccept) &&
                Objects.equals(patientId, that.patientId) &&
                Objects.equals(iId, that.iId) &&
                Objects.equals(doctorId, that.doctorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aId, aDateTime, duration, timestamp, doctorAccept, patientId, iId, doctorId);
    }

    @ManyToOne
    @JoinColumn(name = "PATIENT_ID", referencedColumnName = "P_ID",
            insertable = false, updatable = false, nullable = false)
    public PersonEntity getPatient() {
        return patient;
    }

    public void setPatient(PersonEntity patient) {
        this.patient = patient;
    }

    @ManyToOne
    @JoinColumn(name = "I_ID", referencedColumnName = "I_ID",
            insertable = false, updatable = false, nullable = false)
    public IllnessEntity getIllnessByIId() {
        return illnessByIId;
    }

    public void setIllnessByIId(IllnessEntity illnessByIId) {
        this.illnessByIId = illnessByIId;
    }

    @ManyToOne
    @JoinColumn(name = "DOCTOR_ID", referencedColumnName = "D_ID",
            insertable = false, updatable = false, nullable = false)
    public DoctorEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorEntity doctor) {
        this.doctor = doctor;
    }

    @OneToOne
    @JoinColumn(name = "A_ID", referencedColumnName = "A_ID")
    public PrescriptionEntity getPrescriptionByAId() {
        return prescriptionByAId;
    }

    public void setPrescriptionByAId(PrescriptionEntity prescriptionsByAId) {
        this.prescriptionByAId = prescriptionsByAId;
    }
}
