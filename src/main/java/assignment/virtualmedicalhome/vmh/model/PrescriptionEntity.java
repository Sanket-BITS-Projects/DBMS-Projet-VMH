package assignment.virtualmedicalhome.vmh.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "PRESCRIPTION", schema = "PUBLIC", catalog = "VMHDB.DB")
public class PrescriptionEntity {
    private String description;
    private int courseDuration;
    private Date timestamp;
    private int prescId;
    private int aId;
    private Collection<PrescribedLabTestsEntity> prescribedLabTestsByPrescId;
    private Collection<PrescribedMedicineEntity> prescribedMedicinesByPrescId;
    private AppointmentEntity appointmentByAId;

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

    @OneToMany(mappedBy = "prescriptionByPrescId")
    public Collection<PrescribedLabTestsEntity> getPrescribedLabTestsByPrescId() {
        return prescribedLabTestsByPrescId;
    }

    public void setPrescribedLabTestsByPrescId(Collection<PrescribedLabTestsEntity> prescribedLabTestsByPrescId) {
        this.prescribedLabTestsByPrescId = prescribedLabTestsByPrescId;
    }

    @OneToMany(mappedBy = "prescriptionByPrescId")
    public Collection<PrescribedMedicineEntity> getPrescribedMedicinesByPrescId() {
        return prescribedMedicinesByPrescId;
    }

    public void setPrescribedMedicinesByPrescId(Collection<PrescribedMedicineEntity> prescribedMedicinesByPrescId) {
        this.prescribedMedicinesByPrescId = prescribedMedicinesByPrescId;
    }

    @OneToOne(mappedBy = "prescriptionByAId")
    public AppointmentEntity getAppointmentByAId() {
        return appointmentByAId;
    }

    public void setAppointmentByAId(AppointmentEntity appointmentByAId) {
        this.appointmentByAId = appointmentByAId;
    }
}
