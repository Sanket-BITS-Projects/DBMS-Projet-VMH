package assignment.virtualmedicalhome.vmh.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "FEEDBACK")
public class Feedback {
    private int feedbackId;
    private int rating;
    private String description;
    private Date timestamp;
    private Appointment appointment;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_ID")
    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int fId) {
        this.feedbackId = fId;
    }

    @Basic
    @Column(name = "RATING")
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "TIMESTAMP", columnDefinition = " DATETIME default CURRENT_TIMESTAMP")
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @OneToOne
    @JoinColumn(name = "A_ID", referencedColumnName = "A_ID")
    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return feedbackId == feedback.feedbackId &&
                rating == feedback.rating &&
                description.equals(feedback.description) &&
                timestamp.equals(feedback.timestamp) &&
                appointment.equals(feedback.appointment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackId, rating, description, timestamp, appointment);
    }
}
