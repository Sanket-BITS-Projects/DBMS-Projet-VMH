package assignment.virtualmedicalhome.vmh.repository;

import assignment.virtualmedicalhome.vmh.model.SpecializationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecializationRepository extends CrudRepository<SpecializationEntity, Integer> {
    SpecializationEntity getSpecializationEntityBySpeciality(String speciality);
}
