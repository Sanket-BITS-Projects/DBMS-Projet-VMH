package assignment.virtualmedicalhome.vmh.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "DOCTOR_SPECIALITY", schema = "PUBLIC", catalog = "VMHDB.DB")
@IdClass(DoctorSpecialityEntityPK.class)
public class DoctorSpecialityEntity {
    private int spId;
    private int dId;
    private SpecializationEntity specializationBySpId;
    private DoctorEntity doctorByDId;

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

    @ManyToOne
    @JoinColumn(name = "SP_ID", referencedColumnName = "SP_ID", nullable = false)
    public SpecializationEntity getSpecializationBySpId() {
        return specializationBySpId;
    }

    public void setSpecializationBySpId(SpecializationEntity specializationBySpId) {
        this.specializationBySpId = specializationBySpId;
    }

    @ManyToOne
    @JoinColumn(name = "D_ID", referencedColumnName = "D_ID", nullable = false)
    public DoctorEntity getDoctorByDId() {
        return doctorByDId;
    }

    public void setDoctorByDId(DoctorEntity doctorByDId) {
        this.doctorByDId = doctorByDId;
    }
}
