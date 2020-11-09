package assignment.virtualmedicalhome.vmh.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "PERSON")
@SecondaryTable(name = "People_Wallet", pkJoinColumns = @PrimaryKeyJoinColumn(name = "P_ID"))
public class Person {
    private int id;
    private String name;
    private String email;
    private long phone;
    private Date dob;
    private Role role;

    public Person() {

    }

    public Person(int id, String name, String email, long phone, Date dob, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "P_ID")
    public int getId() {
        return id;
    }

    public void setId(int pId) {
        this.id = pId;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "EMAIL", unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "PHONE")
    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "DOB")
    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @OneToOne
    @JoinTable(
            name = "PERSON_TYPE",
            joinColumns = @JoinColumn(name = "P_ID"),
            inverseJoinColumns = @JoinColumn(name = "P_TYPE")
    )
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                phone == person.phone &&
                name.equals(person.name) &&
                email.equals(person.email) &&
                dob.equals(person.dob) &&
                role.equals(person.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, phone, dob, role);
    }
}
