package assignment.virtualmedicalhome.vmh.repository;

import assignment.virtualmedicalhome.vmh.model.LabTestsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabTestRepository extends CrudRepository<LabTestsEntity, Integer> {

}
