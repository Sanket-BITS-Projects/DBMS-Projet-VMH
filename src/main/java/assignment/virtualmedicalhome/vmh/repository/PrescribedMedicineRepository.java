package assignment.virtualmedicalhome.vmh.repository;

import assignment.virtualmedicalhome.vmh.model.PrescribedMedicineEntity;
import assignment.virtualmedicalhome.vmh.model.PrescribedMedicineEntityPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescribedMedicineRepository extends CrudRepository<PrescribedMedicineEntity, PrescribedMedicineEntityPK> {
}
