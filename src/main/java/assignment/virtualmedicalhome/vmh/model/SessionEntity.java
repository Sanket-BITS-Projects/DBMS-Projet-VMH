package assignment.virtualmedicalhome.vmh.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "SESSION", schema = "PUBLIC", catalog = "VMHDB.DB")
public class SessionEntity {
    private String sessionId;
    private Date timestamp;
    private int pId;
    private PersonEntity personByPId;

    @Basic
    @Column(name = "SESSION_ID")
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Basic
    @Column(name = "TIMESTAMP")
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Id
    @Column(name = "P_ID")
    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionEntity that = (SessionEntity) o;
        return Objects.equals(sessionId, that.sessionId) &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(pId, that.pId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, timestamp, pId);
    }

    @OneToOne
    @JoinColumn(name = "P_ID", referencedColumnName = "P_ID", nullable = false)
    public PersonEntity getPersonByPId() {
        return personByPId;
    }

    public void setPersonByPId(PersonEntity personByPId) {
        this.personByPId = personByPId;
    }
}
