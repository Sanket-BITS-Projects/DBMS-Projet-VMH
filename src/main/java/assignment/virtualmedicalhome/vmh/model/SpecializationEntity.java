package assignment.virtualmedicalhome.vmh.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "SPECIALIZATION")
public class SpecializationEntity {
    private int spId;
    private String speciality;
    private Collection<DoctorEntity> doctorsBySpId;

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

    @JsonIgnore
    @ManyToMany(mappedBy = "specializations")
    public Collection<DoctorEntity> getDoctorsBySpId() {
        return doctorsBySpId;
    }

    public void setDoctorsBySpId(Collection<DoctorEntity> doctorsBySpId) {
        this.doctorsBySpId = doctorsBySpId;
    }
}
