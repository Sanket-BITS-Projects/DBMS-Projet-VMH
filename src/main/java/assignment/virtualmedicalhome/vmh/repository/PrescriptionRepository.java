package assignment.virtualmedicalhome.vmh.repository;

import assignment.virtualmedicalhome.vmh.model.PrescriptionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends CrudRepository<PrescriptionEntity, Integer> {
}
