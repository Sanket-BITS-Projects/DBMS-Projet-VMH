package assignment.virtualmedicalhome.vmh.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class DoctorSpecialityEntityPK implements Serializable {
    private int spId;
    private int dId;

    @Column(name = "SP_ID")
    @Id
    public int getSpId() {
        return spId;
    }

    public void setSpId(int spId) {
        this.spId = spId;
    }

    @Column(name = "D_ID")
    @Id
    public int getdId() {
        return dId;
    }

    public void setdId(int dId) {
        this.dId = dId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorSpecialityEntityPK that = (DoctorSpecialityEntityPK) o;
        return Objects.equals(spId, that.spId) &&
                Objects.equals(dId, that.dId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spId, dId);
    }
}
