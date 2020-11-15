package assignment.virtualmedicalhome.vmh.services;

import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    /*public List<DoctorAppointmentHistory> getHistoryFromAppointments(
            Iterable<Appointment> appointments,
            PersonRepository repository,
            IllnessRepository illnessRepo,
            PrescriptionRepository presRepo,
            DoctorCommissionRepository commissionRepo
    ) {
        ArrayList<DoctorAppointmentHistory> list = new ArrayList<>();
        for (Appointment appointment :
                appointments) {
            DoctorAppointmentHistory history = new DoctorAppointmentHistory();
            history.setAppointment(appointment);
            history.setPatient(repository.findById(appointment.getPatient()).get());
            history.setDoctor(repository.findById(appointment.getDoctor()).get());
            history.setIllness(illnessRepo.findById(appointment.getIllness()).get());
            history.setPrescription(presRepo.findByAppointmentId(appointment.getId()));
            history.setCommission(commissionRepo.findByDoctorId(appointment.getDoctor()));
            list.add(history);
        }
        return list;
    }

    public void prepareAppointmentDetails(
            List<Appointment> appointments, HashMap<Integer, Object> appointmentDetails, int i,
            Person doctor, Prescription prescription, DoctorCommission doctorCommission,
            int appointmentStatus, SimpleDateFormat formatter, HashMap<String, String> dummy) {
        dummy.put("AppointmentId", Integer.toString(appointments.get(i).getId()));
        dummy.put("DoctorName", doctor.getName());
        dummy.put("AppointmentDate", formatter.format(appointments.get(i).getTime()));
        dummy.put("AppointmentStatus", Integer.toString(appointmentStatus));
        if (appointmentStatus == 0) {
            dummy.put("AmountPaid", "");
            dummy.put("prescription", "");
        } else {
            dummy.put("AmountPaid", Integer.toString(doctorCommission.getFees()));
            dummy.put("prescription", prescription.getDescription());
        }
        appointmentDetails.put(i, dummy);
    }*/
}
