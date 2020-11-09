package assignment.virtualmedicalhome.vmh.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ILLNESS")
public class Illness {
    private int illnessId;
    private String title;
    private String description;
    private Date timestamp;

    public  Illness(){

    }


    public Illness(int illnessId, String title, String description, Date timestamp) {
        this.illnessId = illnessId;
        this.title = title;
        this.description = description;
        this.timestamp = timestamp;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "I_ID")
    public int getIllnessId() {
        return illnessId;
    }

    public void setIllnessId(int iId) {
        this.illnessId = iId;
    }

    @Basic
    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        Illness that = (Illness) o;
        return Objects.equals(illnessId, that.illnessId) &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(illnessId, title, description, timestamp);
    }
}
