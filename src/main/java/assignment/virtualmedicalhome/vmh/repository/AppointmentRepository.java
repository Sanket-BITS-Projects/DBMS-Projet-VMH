package assignment.virtualmedicalhome.vmh.repository;

import assignment.virtualmedicalhome.vmh.model.AppointmentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Query(value = "SELECT MAX(TOTAL) AS NO_OF_APPOINTMENTS , ID , NAME FROM(select a.PATIENT_ID AS ID, count(*) AS TOTAL , p.P_NAME AS NAME , a.DOCTOR_ACCEPT from APPOINTMENT AS a JOIN PERSON AS p ON a.PATIENT_ID = p.P_ID where a.DOCTOR_ACCEPT = 1 group by a.PATIENT_ID);", nativeQuery = true)
    Map<String, Object> findFreqPatient();

    @Query(value = "SELECT MAX(TOTAL) AS NO_OF_APPOINTMENTS , ID , NAME FROM(select a.DOCTOR_ID AS ID, count(*) AS TOTAL , p.P_NAME AS NAME , a.DOCTOR_ACCEPT from APPOINTMENT AS a JOIN PERSON AS p ON a.DOCTOR_ID = p.P_ID where a.DOCTOR_ACCEPT = 1 group by a.DOCTOR_ID);", nativeQuery = true)
	Map<String, Object> findTopDoctor();
    
    @Query(value = "SELECT  SUM(0.15*d.FEES) AS  APP_REVENUE  FROM APPOINTMENT AS a JOIN DOCTOR AS d ON a.DOCTOR_ID = d.D_ID WHERE a.A_DATE_TIME = CURRENT_DATE() and a.DOCTOR_ACCEPT = 1;", nativeQuery = true)
	Map<String, Object> findRevenue();
}
