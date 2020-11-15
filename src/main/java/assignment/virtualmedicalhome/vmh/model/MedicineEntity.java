package assignment.virtualmedicalhome.vmh.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "MEDICINE", schema = "PUBLIC", catalog = "VMHDB.DB")
public class MedicineEntity {
    private int mId;
    private String mName;
    private Collection<PrescribedMedicineEntity> prescribedMedicinesByMId;

    @Id
    @Column(name = "M_ID")
    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    @Basic
    @Column(name = "M_NAME")
    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicineEntity that = (MedicineEntity) o;
        return Objects.equals(mId, that.mId) &&
                Objects.equals(mName, that.mName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mId, mName);
    }

    @OneToMany(mappedBy = "medicineByMId")
    public Collection<PrescribedMedicineEntity> getPrescribedMedicinesByMId() {
        return prescribedMedicinesByMId;
    }

    public void setPrescribedMedicinesByMId(Collection<PrescribedMedicineEntity> prescribedMedicinesByMId) {
        this.prescribedMedicinesByMId = prescribedMedicinesByMId;
    }
}
