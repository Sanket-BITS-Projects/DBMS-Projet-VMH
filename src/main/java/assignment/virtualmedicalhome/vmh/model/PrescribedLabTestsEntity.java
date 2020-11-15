package assignment.virtualmedicalhome.vmh.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "PRESCRIBED_LAB_TESTS", schema = "PUBLIC", catalog = "VMHDB.DB")
@IdClass(PrescribedLabTestsEntityPK.class)
public class PrescribedLabTestsEntity {
    private int ltId;
    private int prescId;
    private LabTestsEntity labTestsByLtId;
    private PrescriptionEntity prescriptionByPrescId;

    @Id
    @Column(name = "LT_ID")
    public int getLtId() {
        return ltId;
    }

    public void setLtId(int ltId) {
        this.ltId = ltId;
    }

    @Id
    @Column(name = "PRESC_ID")
    public int getPrescId() {
        return prescId;
    }

    public void setPrescId(int prescId) {
        this.prescId = prescId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrescribedLabTestsEntity that = (PrescribedLabTestsEntity) o;
        return Objects.equals(ltId, that.ltId) &&
                Objects.equals(prescId, that.prescId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ltId, prescId);
    }

    @ManyToOne
    @JoinColumn(name = "LT_ID", referencedColumnName = "LT_ID", nullable = false)
    public LabTestsEntity getLabTestsByLtId() {
        return labTestsByLtId;
    }

    public void setLabTestsByLtId(LabTestsEntity labTestsByLtId) {
        this.labTestsByLtId = labTestsByLtId;
    }

    @ManyToOne
    @JoinColumn(name = "PRESC_ID", referencedColumnName = "PRESC_ID", nullable = false)
    public PrescriptionEntity getPrescriptionByPrescId() {
        return prescriptionByPrescId;
    }

    public void setPrescriptionByPrescId(PrescriptionEntity prescriptionByPrescId) {
        this.prescriptionByPrescId = prescriptionByPrescId;
    }
}
