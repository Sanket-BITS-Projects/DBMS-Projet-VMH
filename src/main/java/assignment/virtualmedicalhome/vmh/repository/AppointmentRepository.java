package assignment.virtualmedicalhome.vmh.repository;

import assignment.virtualmedicalhome.vmh.model.Appointment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {

    List<Appointment> findByPatient(int patient);

    List<Appointment> findByDoctor(int Doctor);

    List<Appointment> findByTime(Date time);

    @Query(value = "select a from Appointment as a where a.patient = :patient and a.doctorAccept = 1 and a.time < :appointmentDate")
    Iterable<Appointment> findAppointmentByPatientTillDate(int patient, Date appointmentDate);

    @Query(value = "select a from Appointment as a where a.doctorAccept = 1 and a.doctor = :doctorId")
    Iterable<Appointment> findHistoryByDoctor(int doctorId);

    @Query(value = "select a from Appointment as a where a.doctorAccept = 0 and a.doctor = :doctorId")
    Iterable<Appointment> findNewByDoctor(int doctorId);
    
    @Query(value = "select * from Appointment where D_ACCEPT = 0 and A_TIME = current_date and D_ID = :Doctor",nativeQuery = true)
    List<Appointment> findDoctorSchedule(@Param("Doctor") int Doctor);
}
