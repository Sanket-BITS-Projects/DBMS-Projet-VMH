package assignment.virtualmedicalhome.vmh.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "SPECIALIZATION", schema = "PUBLIC", catalog = "VMHDB.DB")
public class SpecializationEntity {
    private int spId;
    private String speciality;
    private Collection<DoctorSpecialityEntity> doctorSpecialitiesBySpId;

    @Id
    @Column(name = "SP_ID")
    public int getSpId() {
        return spId;
    }

    public void setSpId(int spId) {
        this.spId = spId;
    }

    @Basic
    @Column(name = "SPECIALITY")
    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecializationEntity that = (SpecializationEntity) o;
        return Objects.equals(spId, that.spId) &&
                Objects.equals(speciality, that.speciality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spId, speciality);
    }

    @OneToMany(mappedBy = "specializationBySpId")
    public Collection<DoctorSpecialityEntity> getDoctorSpecialitiesBySpId() {
        return doctorSpecialitiesBySpId;
    }

    public void setDoctorSpecialitiesBySpId(Collection<DoctorSpecialityEntity> doctorSpecialitiesBySpId) {
        this.doctorSpecialitiesBySpId = doctorSpecialitiesBySpId;
    }
}
