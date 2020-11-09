package assignment.virtualmedicalhome.vmh.repository;

import assignment.virtualmedicalhome.vmh.model.Doctor;
import assignment.virtualmedicalhome.vmh.model.Specialization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Integer> {
    List<Doctor> findAllBySpecialization(Specialization specialization);
}
