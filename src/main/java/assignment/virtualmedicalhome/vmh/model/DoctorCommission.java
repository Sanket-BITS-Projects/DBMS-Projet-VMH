package assignment.virtualmedicalhome.vmh.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "DOCTOR_COMMISSION")
public class DoctorCommission {
    private int id;
    private int doctorId;
    private int fees;

    public DoctorCommission() {
    }

    public DoctorCommission(int id, int doctorId, int fees) {
        this.id = id;
        this.doctorId = doctorId;
        this.fees = fees;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DC_ID")
    public int getId() {
        return id;
    }

    public void setId(int dcId) {
        this.id = dcId;
    }


    @Basic
    @Column(name = "P_ID")
    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    @Basic
    @Column(name = "FEES")
    public int getFees() {
        return fees;
    }

    public void setFees(int fees) {
        this.fees = fees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorCommission that = (DoctorCommission) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(fees, that.fees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorId, fees);
    }
}
