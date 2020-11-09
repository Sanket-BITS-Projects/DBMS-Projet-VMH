package assignment.virtualmedicalhome.vmh.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "SPECIALIZATION")
public class Specialization {
    private int specializationId;
    private String spName;
    private String keywords;

    @Id
    @Column(name = "SP_ID")
    public int getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(int spId) {
        this.specializationId = spId;
    }

    @Basic
    @Column(name = "SP_NAME")
    public String getSpName() {
        return spName;
    }

    public void setSpName(String spName) {
        this.spName = spName;
    }

    @Basic
    @Column(name = "KEYWORDS")
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Specialization that = (Specialization) o;
        return Objects.equals(specializationId, that.specializationId) &&
                Objects.equals(spName, that.spName) &&
                Objects.equals(keywords, that.keywords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(specializationId, spName, keywords);
    }
}
