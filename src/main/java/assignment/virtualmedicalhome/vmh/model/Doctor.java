package assignment.virtualmedicalhome.vmh.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "DOCTOR")
public class Doctor {
    private int doctorId;
    private Date serviceStart;
    private int suspended;
    private Specialization specialization;

    public Doctor(){}

    public Doctor(int doctorId, Date serviceStart, int suspended, Specialization specialization) {
        this.doctorId = doctorId;
        this.serviceStart = serviceStart;
        this.suspended = suspended;
        this.specialization = specialization;
    }

    @Id
    @Column(name = "P_ID")
    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int pId) {
        this.doctorId = pId;
    }

    @Basic
    @Column(name = "SERVICE_START")
    public Date getServiceStart() {
        return serviceStart;
    }

    public void setServiceStart(Date serviceStart) {
        this.serviceStart = serviceStart;
    }

    @Basic
    @Column(name = "SUSPENDED")
    public int getSuspended() {
        return suspended;
    }

    public void setSuspended(int suspended) {
        this.suspended = suspended;
    }

    @OneToOne
    @JoinTable(
            name = "DOCTOR_SPECIALITY",
            joinColumns = @JoinColumn(name = "P_ID"),
            inverseJoinColumns = @JoinColumn(name = "SPECIALITY")
    )
    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return doctorId == doctor.doctorId &&
                suspended == doctor.suspended &&
                serviceStart.equals(doctor.serviceStart) &&
                specialization.equals(doctor.specialization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorId, serviceStart, suspended, specialization);
    }
}
