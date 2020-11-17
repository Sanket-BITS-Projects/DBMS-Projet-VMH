package assignment.virtualmedicalhome.vmh.repository;

import assignment.virtualmedicalhome.vmh.model.IllnessEntity;
import org.springframework.data.repository.CrudRepository;

public interface IllnessReposirory extends CrudRepository<IllnessEntity,Integer> {
}
