package assignment.virtualmedicalhome.vmh.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "PRESCRIBED_MEDICINE")
@IdClass(PrescribedMedicineEntityPK.class)
public class PrescribedMedicineEntity {
    private String usage;
    private int mId;
    private int prescId;
//    private MedicineEntity medicineByMId;
//    private PrescriptionEntity prescriptionByPrescId;

    @Basic
    @Column(name = "USAGE")
    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    @Id
    @Column(name = "M_ID")
    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
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
        PrescribedMedicineEntity that = (PrescribedMedicineEntity) o;
        return Objects.equals(usage, that.usage) &&
                Objects.equals(mId, that.mId) &&
                Objects.equals(prescId, that.prescId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usage, mId, prescId);
    }

//    @ManyToOne
//    @JoinColumn(name = "M_ID", referencedColumnName = "M_ID", nullable = false)
//    public MedicineEntity getMedicineByMId() {
//        return medicineByMId;
//    }
//
//    public void setMedicineByMId(MedicineEntity medicineByMId) {
//        this.medicineByMId = medicineByMId;
//    }
//
//    @ManyToOne
//    @JoinColumn(name = "PRESC_ID", referencedColumnName = "PRESC_ID", nullable = false)
//    public PrescriptionEntity getPrescriptionByPrescId() {
//        return prescriptionByPrescId;
//    }
//
//    public void setPrescriptionByPrescId(PrescriptionEntity prescriptionByPrescId) {
//        this.prescriptionByPrescId = prescriptionByPrescId;
//    }
}
