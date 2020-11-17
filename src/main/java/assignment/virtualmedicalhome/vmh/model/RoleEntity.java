package assignment.virtualmedicalhome.vmh.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ROLE")
public class RoleEntity {
    private String roleName;
    private int roleId;
    private Collection<PersonEntity> peopleByRoleId;

    @Basic
    @Column(name = "ROLE_NAME")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Id
    @Column(name = "ROLE_ID")
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return Objects.equals(roleName, that.roleName) &&
                Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleName, roleId);
    }

    @OneToMany(mappedBy = "role")
    public Collection<PersonEntity> getPeopleByRoleId() {
        return peopleByRoleId;
    }

    public void setPeopleByRoleId(Collection<PersonEntity> peopleByRoleId) {
        this.peopleByRoleId = peopleByRoleId;
    }
}
