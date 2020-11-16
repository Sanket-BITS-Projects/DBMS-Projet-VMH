package assignment.virtualmedicalhome.vmh.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "PRESCRIBED_LAB_TESTS")
public class PrescribedLabTestsEntity implements Serializable {
    private int ltId;
    private int prescId;

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
}
