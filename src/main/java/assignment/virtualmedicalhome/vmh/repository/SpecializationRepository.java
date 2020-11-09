package assignment.virtualmedicalhome.vmh.repository;

import assignment.virtualmedicalhome.vmh.model.Specialization;
import org.springframework.data.repository.CrudRepository;

public interface SpecializationRepository extends CrudRepository<Specialization,Integer> {

    Specialization findBySpName(String spname);
}
