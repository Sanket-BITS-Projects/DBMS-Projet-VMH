package assignment.virtualmedicalhome.vmh.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "DOCTOR_LEAVE")
public class DoctorLeave {
    private int doctorLeaveId;
    private Date offDate;

    @Id
    @Column(name = "DL_ID")
    public int getDoctorLeaveId() {
        return doctorLeaveId;
    }

    public void setDoctorLeaveId(int dlId) {
        this.doctorLeaveId = dlId;
    }

    @Basic
    @Column(name = "OFF_DATE")
    public Date getOffDate() {
        return offDate;
    }

    public void setOffDate(Date offDate) {
        this.offDate = offDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorLeave that = (DoctorLeave) o;
        return Objects.equals(doctorLeaveId, that.doctorLeaveId) &&
                Objects.equals(offDate, that.offDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorLeaveId, offDate);
    }
}
