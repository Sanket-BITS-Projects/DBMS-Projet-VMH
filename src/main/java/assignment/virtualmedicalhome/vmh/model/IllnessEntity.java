package assignment.virtualmedicalhome.vmh.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ILLNESS", schema = "PUBLIC", catalog = "VMHDB.DB")
public class IllnessEntity {
    private int iId;
    private String title;
    private String description;
    private Collection<AppointmentEntity> appointmentsByIId;

    @Id
    @Column(name = "I_ID")
    public int getiId() {
        return iId;
    }

    public void setiId(int iId) {
        this.iId = iId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IllnessEntity that = (IllnessEntity) o;
        return Objects.equals(iId, that.iId) &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iId, title, description);
    }

    @OneToMany(mappedBy = "illnessByIId")
    public Collection<AppointmentEntity> getAppointmentsByIId() {
        return appointmentsByIId;
    }

    public void setAppointmentsByIId(Collection<AppointmentEntity> appointmentsByIId) {
        this.appointmentsByIId = appointmentsByIId;
    }
}
