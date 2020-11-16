package assignment.virtualmedicalhome.vmh.repository;

import assignment.virtualmedicalhome.vmh.model.AppointmentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface AppointmentRepository extends CrudRepository<AppointmentEntity, Integer> {
    @Query(value = "select a from AppointmentEntity as a where a.patientId = :patient and a.doctorAccept = 1 and a.timestamp < :endDate")
    Iterable<AppointmentEntity> findAppointmentByPatientTillDate(int patient, Date endDate);

    @Query(value = "select a from AppointmentEntity as a where a.doctorAccept = 1 and a.doctorId = :doctorId")
    Iterable<AppointmentEntity> findHistoryByDoctor(int doctorId);

    @Query(value = "select a from AppointmentEntity as a where a.doctorAccept = 0 and a.doctorId = :doctorId")
    Iterable<AppointmentEntity> findNewByDoctor(int doctorId);

    @Query(value = "select * from AppointmentEntity as a where a.doctor_Accept = 0 and a.a_Date_Time = current_date and a.doctor_Id = :Doctor", nativeQuery = true)
    List<AppointmentEntity> findDoctorSchedule(@Param("Doctor") int doctorId);
}
