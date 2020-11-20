package assignment.virtualmedicalhome.vmh.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "PRESCRIPTION")
public class PrescriptionEntity {
    private String description;
    private int courseDuration;
    private Date timestamp;
    private int prescId;
    private int aId;
    private Collection<LabTestsEntity> labTestsByPrescId;
    private Collection<MedicineEntity> medicinesByPrescId;
    private AppointmentEntity appointmentByAId;

    public PrescriptionEntity() {

    }

    public PrescriptionEntity(int aId, String description, int courseDuration) {
        this.aId = aId;
        this.description = description;
        this.courseDuration = courseDuration;
        this.timestamp = Calendar.getInstance().getTime();
    }

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "COURSE_DURATION")
    public int getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(int courseDuration) {
        this.courseDuration = courseDuration;
    }

    @Basic
    @Column(name = "TIMESTAMP")
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRESC_ID")
    public int getPrescId() {
        return prescId;
    }

    public void setPrescId(int prescId) {
        this.prescId = prescId;
    }

    @Basic
    @Column(name = "A_ID")
    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrescriptionEntity that = (PrescriptionEntity) o;
        return Objects.equals(description, that.description) &&
                Objects.equals(courseDuration, that.courseDuration) &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(prescId, that.prescId) &&
                Objects.equals(aId, that.aId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, courseDuration, timestamp, prescId, aId);
    }

    @ManyToMany
    @JoinTable(name = "PRESCRIBED_LAB_TESTS", joinColumns = @JoinColumn(name = "PRESC_ID"),
            inverseJoinColumns = @JoinColumn(name = "LT_ID"))
    public Collection<LabTestsEntity> getLabTestsByPrescId() {
        return labTestsByPrescId;
    }

    public void setLabTestsByPrescId(Collection<LabTestsEntity> labTestsByPrescId) {
        this.labTestsByPrescId = labTestsByPrescId;
    }

    @ManyToMany
    @JoinTable(name = "PRESCRIBED_MEDICINE", joinColumns = @JoinColumn(name = "PRESC_ID"),
            inverseJoinColumns = @JoinColumn(name = "M_ID"))
    public Collection<MedicineEntity> getMedicinesByPrescId() {
        return medicinesByPrescId;
    }

    public void setMedicinesByPrescId(Collection<MedicineEntity> medicinesByPrescId) {
        this.medicinesByPrescId = medicinesByPrescId;
    }


    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "A_ID", referencedColumnName = "A_ID",insertable = false ,updatable = false)
    public AppointmentEntity getAppointmentByAId() {
        return appointmentByAId;
    }

    public void setAppointmentByAId(AppointmentEntity appointmentByAId) {
        this.appointmentByAId = appointmentByAId;
    }
}
