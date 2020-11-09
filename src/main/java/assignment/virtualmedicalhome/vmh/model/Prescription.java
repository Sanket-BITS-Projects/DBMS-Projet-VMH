package assignment.virtualmedicalhome.vmh.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "PRESCRIPTION")
public class Prescription {
    private int prescriptionId;
    private int appointmentId;
    private String description;
    private int courseDuration;
    private Date timestamp;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PR_ID")
    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prId) {
        this.prescriptionId = prId;
    }

    @Basic
    @Column(name = "A_ID")
    public  int getAppointmentId(){return appointmentId;}

    public  void setAppointmentId(int aid){this.appointmentId = aid;}

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "COURSE_DURATION")
    public int getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(int courseDuration) {
        this.courseDuration = courseDuration;
    }

    @Basic
    @Column(name = "TIMESTAMP")
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prescription that = (Prescription) o;
        return Objects.equals(prescriptionId, that.prescriptionId) &&
                Objects.equals(appointmentId,that.appointmentId)&&
                Objects.equals(description, that.description) &&
                Objects.equals(courseDuration, that.courseDuration) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prescriptionId, appointmentId,description, courseDuration, timestamp);
    }
}
