package assignment.virtualmedicalhome.vmh.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "LAB_TESTS", schema = "PUBLIC", catalog = "VMHDB.DB")
public class LabTestsEntity {
    private int ltId;
    private String ltName;
    private Collection<PrescriptionEntity> prescriptionsByLtId;

    @Id
    @Column(name = "LT_ID")
    public int getLtId() {
        return ltId;
    }

    public void setLtId(int ltId) {
        this.ltId = ltId;
    }

    @Basic
    @Column(name = "LT_NAME")
    public String getLtName() {
        return ltName;
    }

    public void setLtName(String ltName) {
        this.ltName = ltName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LabTestsEntity that = (LabTestsEntity) o;
        return Objects.equals(ltId, that.ltId) &&
                Objects.equals(ltName, that.ltName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ltId, ltName);
    }

    @ManyToMany(mappedBy = "labTestsByPrescId")
    public Collection<PrescriptionEntity> getPrescriptionsByLtId() {
        return prescriptionsByLtId;
    }

    public void setPrescriptionsByLtId(Collection<PrescriptionEntity> prescriptionsByLtId) {
        this.prescriptionsByLtId = prescriptionsByLtId;
    }
}
