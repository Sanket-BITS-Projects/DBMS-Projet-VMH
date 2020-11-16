package assignment.virtualmedicalhome.vmh.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "DOCTOR_SPECIALITY")
public class DoctorSpecialityEntity implements Serializable {
    private int spId;
    private int dId;

    @Id
    @Column(name = "SP_ID")
    public int getSpId() {
        return spId;
    }

    public void setSpId(int spId) {
        this.spId = spId;
    }

    @Id
    @Column(name = "D_ID")
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
        DoctorSpecialityEntity that = (DoctorSpecialityEntity) o;
        return Objects.equals(spId, that.spId) &&
                Objects.equals(dId, that.dId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spId, dId);
    }
}
