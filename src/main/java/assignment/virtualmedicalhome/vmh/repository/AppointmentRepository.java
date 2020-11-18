package assignment.virtualmedicalhome.vmh.repository;

import assignment.virtualmedicalhome.vmh.model.AppointmentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentRepository extends CrudRepository<AppointmentEntity, Integer> {
    @Query(value = "select a from AppointmentEntity as a where a.patientId = :patient and a.doctorAccept = 1 and a.timestamp < :endDate")
    Iterable<AppointmentEntity> findAppointmentByPatientTillDate(int patient, Date endDate);

    @Query(value = "select a from AppointmentEntity as a where a.doctorAccept = '1' and a.doctorId = :doctorId")
    ArrayList<AppointmentEntity> findHistoryByDoctor(int doctorId);

    @Query(value = "select * from Appointment as a where a.DOCTOR_ACCEPT = '0' and a.DOCTOR_ID = :doctorId", nativeQuery = true)
    ArrayList<AppointmentEntity> findNewByDoctor(@Param("doctorId")int DOCTOR_ID);

    @Query(value = "select * from AppointmentEntity as a where a.doctor_Accept = 0 and a.a_Date_Time = current_date and a.doctor_Id = :Doctor", nativeQuery = true)
    List<AppointmentEntity> findDoctorSchedule(@Param("Doctor") int doctorId);

    @Query(value = "select * from APPOINTMENT as a where a.DOCTOR_ACCEPT = 1 and a.A_DATE_TIME = :app_date", nativeQuery = true)
    ArrayList<AppointmentEntity> findByTime(@Param("app_date") Date A_DATE_TIME);
}
