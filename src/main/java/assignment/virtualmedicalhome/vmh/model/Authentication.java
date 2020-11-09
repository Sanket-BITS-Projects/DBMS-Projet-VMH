package assignment.virtualmedicalhome.vmh.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "AUTHENTICATION")
public class Authentication {
    private int personId;
    private String username;
    private String password;
    private String sessionId;
    private Date sessionEnd;

    @Id
    @Column(name = "P_ID")
    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int pId) {
        this.personId = pId;
    }

    @Basic
    @Column(name = "USERNAME")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "SESSION_ID")
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Basic
    @Column(name = "SESSION_END")
    public Date getSessionEnd() {
        return sessionEnd;
    }

    public void setSessionEnd(Date sessionEnd) {
        this.sessionEnd = sessionEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authentication that = (Authentication) o;
        return Objects.equals(personId, that.personId) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(sessionId, that.sessionId) &&
                Objects.equals(sessionEnd, that.sessionEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, username, password, sessionId, sessionEnd);
    }
}
