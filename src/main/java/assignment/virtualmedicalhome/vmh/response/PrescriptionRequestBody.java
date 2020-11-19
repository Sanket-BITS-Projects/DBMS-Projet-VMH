package assignment.virtualmedicalhome.vmh.response;

import assignment.virtualmedicalhome.vmh.model.LabTestsEntity;
import assignment.virtualmedicalhome.vmh.model.PrescribedMedicineEntity;

import java.util.HashSet;

public class PrescriptionRequestBody {
    private String description;
    private int courseDuration;
    private int aId;
    private HashSet<PrescribedMedicineEntity> prescribedMedicineEntities;
    private HashSet<LabTestsEntity> labTestsEntities;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(int courseDuration) {
        this.courseDuration = courseDuration;
    }


    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    public HashSet<PrescribedMedicineEntity> getPrescribedMedicineEntities() {
        return prescribedMedicineEntities;
    }

    public void setPrescribedMedicineEntities(HashSet<PrescribedMedicineEntity> prescribedMedicineEntities) {
        this.prescribedMedicineEntities = prescribedMedicineEntities;
    }

    public HashSet<LabTestsEntity> getLabTestsEntities() {
        return labTestsEntities;
    }

    public void setLabTestsEntities(HashSet<LabTestsEntity> labTestsEntities) {
        this.labTestsEntities = labTestsEntities;
    }
}
