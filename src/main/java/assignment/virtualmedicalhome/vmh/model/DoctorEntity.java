package assignment.virtualmedicalhome.vmh.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "DOCTOR", schema = "PUBLIC", catalog = "VMHDB.DB")
public class DoctorEntity {
    private int fees;
    private int dId;
    private Collection<AppointmentEntity> appointmentsByDId;
    private PersonEntity personByDId;
    private Collection<DoctorSpecialityEntity> doctorSpecialitiesByDId;

    @Basic
    @Column(name = "FEES")
    public int getFees() {
        return fees;
    }

    public void setFees(int fees) {
        this.fees = fees;
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
        DoctorEntity that = (DoctorEntity) o;
        return Objects.equals(fees, that.fees) &&
                Objects.equals(dId, that.dId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fees, dId);
    }

    @OneToMany(mappedBy = "doctorByDoctorId")
    public Collection<AppointmentEntity> getAppointmentsByDId() {
        return appointmentsByDId;
    }

    public void setAppointmentsByDId(Collection<AppointmentEntity> appointmentsByDId) {
        this.appointmentsByDId = appointmentsByDId;
    }

    @OneToOne
    @JoinColumn(name = "D_ID", referencedColumnName = "P_ID", nullable = false)
    public PersonEntity getPersonByDId() {
        return personByDId;
    }

    public void setPersonByDId(PersonEntity personByDId) {
        this.personByDId = personByDId;
    }

    @OneToMany(mappedBy = "doctorByDId")
    public Collection<DoctorSpecialityEntity> getDoctorSpecialitiesByDId() {
        return doctorSpecialitiesByDId;
    }

    public void setDoctorSpecialitiesByDId(Collection<DoctorSpecialityEntity> doctorSpecialitiesByDId) {
        this.doctorSpecialitiesByDId = doctorSpecialitiesByDId;
    }
}