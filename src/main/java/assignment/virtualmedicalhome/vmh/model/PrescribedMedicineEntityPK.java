package assignment.virtualmedicalhome.vmh.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class PrescribedMedicineEntityPK implements Serializable {
    private int mId;
    private int prescId;

    @Column(name = "M_ID")
    @Id
    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
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
        PrescribedMedicineEntityPK that = (PrescribedMedicineEntityPK) o;
        return Objects.equals(mId, that.mId) &&
                Objects.equals(prescId, that.prescId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mId, prescId);
    }
}
