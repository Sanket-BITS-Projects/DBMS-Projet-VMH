package assignment.virtualmedicalhome.vmh.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "PEOPLE_WALLET")
public class PeopleWallet {
    private int personId;
    private int balance;

    @Id
    @Column(name = "P_ID")
    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int pId) {
        this.personId = pId;
    }

    @Basic
    @Column(name = "BALANCE")
    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeopleWallet that = (PeopleWallet) o;
        return Objects.equals(personId, that.personId) &&
                Objects.equals(balance, that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, balance);
    }
}
