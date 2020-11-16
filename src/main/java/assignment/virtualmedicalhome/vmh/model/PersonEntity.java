package assignment.virtualmedicalhome.vmh.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "PERSON", schema = "PUBLIC", catalog = "VMHDB.DB")
public class PersonEntity {
    private String pName;
    private Date dob;
    private String phone;
    private int pId;
    private String email;
    private String password;
    private int balance;
    private String address;
    private int roleId;
    private Collection<AppointmentEntity> appointmentsByPId;
    private DoctorEntity doctorByPId;
    private RoleEntity roleByRoleId;
    private SessionEntity sessionByPId;

    @Basic
    @Column(name = "P_NAME")
    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    @Basic
    @Column(name = "DOB")
    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Basic
    @Column(name = "PHONE")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Id
    @Column(name = "P_ID")
    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    @Basic
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    @Column(name = "BALANCE")
    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Basic
    @Column(name = "ADDRESS")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
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
        PersonEntity that = (PersonEntity) o;
        return Objects.equals(pName, that.pName) &&
                Objects.equals(dob, that.dob) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(pId, that.pId) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                Objects.equals(balance, that.balance) &&
                Objects.equals(address, that.address) &&
                Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pName, dob, phone, pId, email, password, balance, address, roleId);
    }

    @OneToMany(mappedBy = "patient")
    public Collection<AppointmentEntity> getAppointmentsByPId() {
        return appointmentsByPId;
    }

    public void setAppointmentsByPId(Collection<AppointmentEntity> appointmentsByPId) {
        this.appointmentsByPId = appointmentsByPId;
    }

    @OneToOne(mappedBy = "personByDId")
    public DoctorEntity getDoctorByPId() {
        return doctorByPId;
    }

    public void setDoctorByPId(DoctorEntity doctorByPId) {
        this.doctorByPId = doctorByPId;
    }

    @ManyToOne
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID",
            insertable = false, updatable = false, nullable = false)
    public RoleEntity getRoleByRoleId() {
        return roleByRoleId;
    }

    public void setRoleByRoleId(RoleEntity roleByRoleId) {
        this.roleByRoleId = roleByRoleId;
    }

    @OneToOne(mappedBy = "personByPId")
    public SessionEntity getSessionByPId() {
        return sessionByPId;
    }

    public void setSessionByPId(SessionEntity sessionByPId) {
        this.sessionByPId = sessionByPId;
    }
}
