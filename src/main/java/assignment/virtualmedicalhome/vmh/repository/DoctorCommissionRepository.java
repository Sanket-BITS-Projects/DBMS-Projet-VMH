package assignment.virtualmedicalhome.vmh.repository;

import assignment.virtualmedicalhome.vmh.model.DoctorCommission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorCommissionRepository extends CrudRepository<DoctorCommission,Integer> {
    DoctorCommission findByDoctorId(int doctorId);
}
