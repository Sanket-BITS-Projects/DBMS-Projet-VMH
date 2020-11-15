package assignment.virtualmedicalhome.vmh.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class PrescribedLabTestsEntityPK implements Serializable {
    private int ltId;
    private int prescId;

    @Column(name = "LT_ID")
    @Id
    public int getLtId() {
        return ltId;
    }

    public void setLtId(int ltId) {
        this.ltId = ltId;
    }

    @Column(name = "PRESC_ID")
    @Id
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
        PrescribedLabTestsEntityPK that = (PrescribedLabTestsEntityPK) o;
        return Objects.equals(ltId, that.ltId) &&
                Objects.equals(prescId, that.prescId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ltId, prescId);
    }
}
