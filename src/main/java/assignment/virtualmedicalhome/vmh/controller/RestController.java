package assignment.virtualmedicalhome.vmh.controller;

import assignment.virtualmedicalhome.vmh.model.*;
import assignment.virtualmedicalhome.vmh.repository.*;
import assignment.virtualmedicalhome.vmh.response.GenericResponse;
import assignment.virtualmedicalhome.vmh.services.AppointmentService;
import assignment.virtualmedicalhome.vmh.services.PersonService;
import jdk.nashorn.internal.runtime.Specialization;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    private final PersonRepository repository;
    private final SessionRepository authRepo;
    private final RoleRepository roleRepo;
    private final AppointmentRepository appointmentRepo;
    private final PersonService personService;
    private final AppointmentService appointmentService;
    private final SpecializationRepository specialRepo;
    private final DoctorRepository docRepo;

    public RestController(
            PersonRepository repository,
            SessionRepository authRepo,
            AppointmentRepository appointmentRepo,
            PersonService personService,
            RoleRepository roleRepo,
            AppointmentService appointmentService,
            SpecializationRepository specialRepo,
            DoctorRepository docRepo) {
        this.repository = repository;
        this.authRepo = authRepo;
        this.appointmentRepo = appointmentRepo;
        this.personService = personService;
        this.roleRepo = roleRepo;
        this.appointmentService = appointmentService;
        this.specialRepo = specialRepo;
        this.docRepo = docRepo;
    }

    @GetMapping("/test")
    public ResponseEntity<GenericResponse> test() {
        return GenericResponse.getSuccessResponse("Good to go");
    }

    /*@PostMapping("/SearchDoctor")
    public ResponseEntity<GenericResponse> SearchDoctor(@CookieValue(name = "SESSION_ID", required = false) String sessionId,
                                                        @RequestParam String spName) {
        try {
            Authentication auth = personService.getAuthentication(authRepo, sessionId);
            Person person = personService.getAuthorizedPatient(repository, auth);
            Specialization specialization = specialRepo.findBySpName(spName);
            List<Doctor> doctors = docRepo.findAllBySpecialization(specialization);
            HashMap<Integer, Object> doctorDetails = new HashMap<>();
            int size = doctors.size();
            for (int i = 0; i < size; i++) {
                Person doctor = repository.findById(doctors.get(i).getDoctorId()).get();
                DoctorCommission doctorCommission = commissionRepo.findByDoctorId(doctors.get(i).getDoctorId());
                Calendar c = Calendar.getInstance();
                c.setTime(doctors.get(i).getServiceStart());
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH) + 1;
                int date = c.get(Calendar.DATE);
                LocalDate l = LocalDate.of(year, month, date);
                LocalDate now = LocalDate.now();
                Period diff = Period.between(l, now);
                HashMap<String, String> dummy = new HashMap<>();
                dummy.put("DoctorID", Integer.toString(doctors.get(i).getDoctorId()));
                dummy.put("DoctorName", doctor.getName());
                dummy.put("DoctorExperience", Integer.toString(diff.getYears()));
                dummy.put("DoctorSpeciality", specialization.getSpName());
                dummy.put("DoctorsExpertFields", specialization.getKeywords());
                dummy.put("DoctorCommission", Integer.toString(doctorCommission.getFees()));
                doctorDetails.put(i, dummy);
            }
            return GenericResponse.getSuccessResponse(doctorDetails);
        } catch (InvalidSessionException | UnauthorizedException e) {
            e.printStackTrace();
            return GenericResponse.getFailureResponse(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponse.getFailureResponse(
                    "Something went wrong, please contact admin!",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @RequestMapping("/AppointmentlistofPatient")
    public ResponseEntity<GenericResponse> GetPAppointmentList(@CookieValue(name = "SESSION_ID", required = false) String sessionId,
                                                               @RequestParam int Patient) {
        try {
            Authentication auth = personService.getAuthentication(authRepo, sessionId);
            Person person = personService.getAuthorizedAdmin(repository, auth);

            List<Appointment> appointments = appointmentRepo.findByPatient(Patient);
            return getAppointmentListAsResponse(appointments);
        } catch (InvalidSessionException | UnauthorizedException e) {
            e.printStackTrace();
            return GenericResponse.getFailureResponse(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponse.getFailureResponse(
                    "Something went wrong, contact admin!",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @RequestMapping("/Feedbacklist")
    public ResponseEntity<GenericResponse> GetFeedbackList(@CookieValue(name = "SESSION_ID", required = false) String sessionId) {
        {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Authentication auth = personService.getAuthentication(authRepo, sessionId);
                Person person = personService.getAuthorizedAdmin(repository, auth);
                List<Feedback> feedbacks = feedbackRepo.findAll();
                HashMap<Integer, Object> feedbackdetails = new HashMap<Integer, Object>();
                int size = feedbacks.size();
                for (int i = 0; i < size; i++) {
                    Appointment appointments = feedbacks.get(i).getAppointment();
                    Person Patient = repository.findById(appointments.getPatient()).get();
                    Person Doctor = repository.findById(appointments.getDoctor()).get();
                    HashMap<String, String> dummy = new HashMap<String, String>();
                    dummy.put("FeedbackId", Integer.toString(feedbacks.get(i).getFeedbackId()));
                    dummy.put("AppointmentId", Integer.toString(appointments.getId()));
                    dummy.put("DoctorName", Doctor.getName());
                    dummy.put("PatientName", Patient.getName());
                    dummy.put("Rating", Integer.toString(feedbacks.get(i).getRating()));
                    dummy.put("Description", feedbacks.get(i).getDescription());
                    dummy.put("Date", formatter.format(feedbacks.get(i).getTimestamp()));
                    feedbackdetails.put(i, dummy);
                }
                return GenericResponse.getSuccessResponse(feedbackdetails);
            } catch (InvalidSessionException | UnauthorizedException e) {
                e.printStackTrace();
                return GenericResponse.getFailureResponse(e.getMessage(), HttpStatus.UNAUTHORIZED);
            } catch (Exception e) {
                e.printStackTrace();
                return GenericResponse.getFailureResponse(
                        "Something went wrong, contact admin!",
                        HttpStatus.INTERNAL_SERVER_ERROR
                );
            }
        }
    }

    @RequestMapping("/FeedbacklistByRating")
    public ResponseEntity<GenericResponse> GetFeedbackListbyRating(@CookieValue(name = "SESSION_ID", required = false) String sessionId, @RequestParam int Rating) {
        {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Authentication auth = personService.getAuthentication(authRepo, sessionId);
                Person person = personService.getAuthorizedAdmin(repository, auth);
                List<Feedback> feedbacks = feedbackRepo.findByRating(Rating);
                HashMap<Integer, Object> feedbackDetails = new HashMap<>();
                int size = feedbacks.size();
                for (int i = 0; i < size; i++) {
                    Appointment appointments = feedbacks.get(i).getAppointment();
                    Person Patient = repository.findById(appointments.getPatient()).get();
                    Person Doctor = repository.findById(appointments.getDoctor()).get();
                    HashMap<String, String> dummy = new HashMap<String, String>();
                    dummy.put("FeedbackId", Integer.toString(feedbacks.get(i).getFeedbackId()));
                    dummy.put("AppointmentId", Integer.toString(appointments.getId()));
                    dummy.put("DoctorName", Doctor.getName());
                    dummy.put("PatientName", Patient.getName());
                    dummy.put("Rating", Integer.toString(Rating));
                    dummy.put("Description", feedbacks.get(i).getDescription());
                    dummy.put("Date", formatter.format(feedbacks.get(i).getTimestamp()));
                    feedbackDetails.put(i, dummy);
                }
                return GenericResponse.getSuccessResponse(feedbackDetails);
            } catch (InvalidSessionException | UnauthorizedException e) {
                e.printStackTrace();
                return GenericResponse.getFailureResponse(e.getMessage(), HttpStatus.UNAUTHORIZED);
            } catch (Exception e) {
                e.printStackTrace();
                return GenericResponse.getFailureResponse(
                        "Something went wrong, contact admin!",
                        HttpStatus.INTERNAL_SERVER_ERROR
                );
            }
        }
    }

    @RequestMapping("/AppointmentlistbyDate")
    public ResponseEntity<GenericResponse> GetTAppointmentList(@CookieValue(name = "SESSION_ID", required = false) String sessionId,
                                                               @RequestParam String Date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date app_date;
        try {
            app_date = formatter.parse(Date);
        } catch (ParseException parseException) {
            return GenericResponse.getFailureResponse(
                    "Unknown date format! Date format should be dd-MM-yyyy",
                    HttpStatus.BAD_REQUEST);
        }
        try {
            Authentication auth = personService.getAuthentication(authRepo, sessionId);
            Person person = personService.getAuthorizedAdmin(repository, auth);
            List<Appointment> appointments = appointmentRepo.findByTime(app_date);
            HashMap<Integer, Object> appointmentdetails = new HashMap<Integer, Object>();
            int size = appointments.size();
            for (int i = 0; i < size; i++) {
                Person patient = repository.findById(appointments.get(i).getPatient()).get();
                Person Doctor = repository.findById(appointments.get(i).getDoctor()).get();
                Prescription prescription = presRepo.findByAppointmentId(appointments.get(i).getId());
                DoctorCommission doctorCommission = commissionRepo.findByDoctorId(appointments.get(i).getDoctor());
                int appointmentstatus = appointments.get(i).getDoctorAccept();
                HashMap<String, String> dummy = new HashMap<String, String>();
                dummy.put("AppointmentId", Integer.toString(appointments.get(i).getId()));
                dummy.put("DoctorId", Integer.toString(Doctor.getId()));
                dummy.put("DoctorName", Doctor.getName());
                dummy.put("PatientName", patient.getName());
                dummy.put("PatientId", Integer.toString(patient.getId()));
                dummy.put("AppointmentDate", formatter.format(appointments.get(i).getTime()));
                dummy.put("AppointmentStatus", Integer.toString(appointmentstatus));
                if (appointmentstatus == 0) {
                    dummy.put("Fees", "");
                    dummy.put("prescription", "");
                } else {
                    dummy.put("Fees", Integer.toString(doctorCommission.getFees()));
                    dummy.put("prescription", prescription.getDescription());
                }
                appointmentdetails.put(i, dummy);
            }

            return GenericResponse.getSuccessResponse(appointmentdetails);
        } catch (InvalidSessionException | UnauthorizedException e) {
            e.printStackTrace();
            return GenericResponse.getFailureResponse(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponse.getFailureResponse(
                    "Something went wrong, contact admin!",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @RequestMapping("/AppointmentlistofDoctor")
    public ResponseEntity<GenericResponse> GetDAppointmentList(@CookieValue(name = "SESSION_ID", required = false) String sessionId,
                                                               @RequestParam int Doctor) {
        try {
            Authentication auth = personService.getAuthentication(authRepo, sessionId);
            Person person = personService.getAuthorizedAdmin(repository, auth);
            List<Appointment> appointments = appointmentRepo.findByDoctor(Doctor);
            HashMap<Integer, Object> appointmentDetails = new HashMap<>();
            int size = appointments.size();
            for (int i = 0; i < size; i++) {
                Person patient = repository.findById(appointments.get(i).getPatient()).get();
                Prescription prescription = presRepo.findByAppointmentId(appointments.get(i).getId());
                DoctorCommission doctorCommission = commissionRepo.findByDoctorId(Doctor);
                int appointmentStatus = appointments.get(i).getDoctorAccept();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                HashMap<String, String> dummy = new HashMap<>();
                if (appointmentStatus == 0) {
                    dummy.put("AppointmentId", Integer.toString(appointments.get(i).getId()));
                    dummy.put("PatientName", patient.getName());
                    dummy.put("AppointmentDate", formatter.format(appointments.get(i).getTime()));
                    dummy.put("AppointmentStatus", Integer.toString(appointmentStatus));
                    dummy.put("FeesCollected", "");
                    dummy.put("prescription", "");
                    appointmentDetails.put(i, dummy);
                } else {
                    dummy.put("AppointmentId", Integer.toString(appointments.get(i).getId()));
                    dummy.put("PatientName", patient.getName());
                    dummy.put("AppointmentDate", formatter.format(appointments.get(i).getTime()));
                    dummy.put("AppointmentStatus", Integer.toString(appointmentStatus));
                    dummy.put("FeesCollected", Integer.toString(doctorCommission.getFees()));
                    dummy.put("prescription", prescription.getDescription());
                    appointmentDetails.put(i, dummy);
                }
            }
            return GenericResponse.getSuccessResponse(appointmentDetails);
        } catch (InvalidSessionException | UnauthorizedException e) {
            e.printStackTrace();
            return GenericResponse.getFailureResponse(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponse.getFailureResponse(
                    "Something went wrong, contact admin!",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @RequestMapping("/DoctorSchedule")
    public ResponseEntity<GenericResponse> getAppointmentSchedule(
            @CookieValue("SESSION_ID") String sessionId, @RequestParam int Doctor
    ) {
        try {
            Authentication auth = personService.getAuthentication(authRepo, sessionId);
            Person person = personService.getAuthorizedAdmin(repository, auth);
            List<Appointment> appointments = appointmentRepo.findDoctorSchedule(Doctor);
            HashMap<Integer, Object> appointmentDetails = new HashMap<>();
            int size = appointments.size();
            for (int i = 0; i < size; i++) {
                Person patient = repository.findById(appointments.get(i).getPatient()).get();
                Prescription prescription = presRepo.findByAppointmentId(appointments.get(i).getId());
                DoctorCommission doctorCommission = commissionRepo.findByDoctorId(Doctor);
                int appointmentStatus = appointments.get(i).getDoctorAccept();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                HashMap<String, String> dummy = new HashMap<>();
                dummy.put("AppointmentId", Integer.toString(appointments.get(i).getId()));
                dummy.put("PatientName", patient.getName());
                dummy.put("AppointmentDate", formatter.format(appointments.get(i).getTime()));
                dummy.put("AppointmentStatus", Integer.toString(appointmentStatus));
                dummy.put("Fees To be Collected", Integer.toString(doctorCommission.getFees()));
                appointmentDetails.put(i, dummy);
            }
            return GenericResponse.getSuccessResponse(appointmentDetails);
        } catch (InvalidSessionException | UnauthorizedException e) {
            e.printStackTrace();
            return GenericResponse.getFailureResponse(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponse.getFailureResponse(
                    "Something went wrong, contact admin!",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }


    @RequestMapping("/PatientAppointmentHistory")
    public ResponseEntity<GenericResponse> GetAppointment(@CookieValue(name = "SESSION_ID", required = false) String sessionId) {
        try {
            Authentication auth = personService.getAuthentication(authRepo, sessionId);
            Person person = personService.getAuthorizedPatient(repository, auth);

            List<Appointment> appointments = appointmentRepo.findByPatient(auth.getPersonId());
            return getAppointmentListAsResponse(appointments);
        } catch (InvalidSessionException | UnauthorizedException e) {
            e.printStackTrace();
            return GenericResponse.getFailureResponse(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponse.getFailureResponse(
                    "Something went wrong, contact admin!",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    private ResponseEntity<GenericResponse> getAppointmentListAsResponse(List<Appointment> appointments) {
        HashMap<Integer, Object> appointmentDetails = new HashMap<>();
        int size = appointments.size();
        for (int i = 0; i < size; i++) {
            Person doctor = repository.findById(appointments.get(i).getDoctor()).get();
            Prescription prescription = presRepo.findByAppointmentId(appointments.get(i).getId());
            DoctorCommission doctorCommission = commissionRepo.findByDoctorId(appointments.get(i).getDoctor());
            int appointmentStatus = appointments.get(i).getDoctorAccept();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            HashMap<String, String> dummy = new HashMap<>();
            appointmentService.prepareAppointmentDetails(
                    appointments, appointmentDetails, i, doctor, prescription,
                    doctorCommission, appointmentStatus, formatter, dummy);
        }
        return GenericResponse.getSuccessResponse(appointmentDetails);
    }

    @RequestMapping("/doctorAppointmentHistory")
    public ResponseEntity<GenericResponse> getAppointmentHistory(
            @CookieValue("SESSION_ID") String sessionId
    ) {
        try {
            Authentication auth = personService.getAuthentication(authRepo, sessionId);
            Person person = personService.getAuthorizedDoctor(repository, auth);
            Iterable<Appointment> oldAppointments = appointmentRepo.findHistoryByDoctor(person.getId());
            List<DoctorAppointmentHistory> list = appointmentService.getHistoryFromAppointments(
                    oldAppointments,
                    repository,
                    illnessRepo,
                    presRepo,
                    commissionRepo
            );
            return GenericResponse.getSuccessResponse(list);
        } catch (InvalidSessionException | UnauthorizedException e) {
            e.printStackTrace();
            return GenericResponse.getFailureResponse(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponse.getFailureResponse(
                    "Something went wrong, contact admin!",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @RequestMapping("/doctorAppointmentsPending")
    public ResponseEntity<GenericResponse> getNewDoctorAppointments(
            @CookieValue("SESSION_ID") String sessionId
    ) {
        try {
            Authentication auth = personService.getAuthentication(authRepo, sessionId);
            Person person = personService.getAuthorizedDoctor(repository, auth);
            Iterable<Appointment> newAppointments = appointmentRepo.findNewByDoctor(person.getId());
            List<DoctorAppointmentHistory> list = appointmentService.getHistoryFromAppointments(
                    newAppointments,
                    repository,
                    illnessRepo,
                    presRepo,
                    commissionRepo

            );
            return GenericResponse.getSuccessResponse(list);
        } catch (InvalidSessionException | UnauthorizedException e) {
            e.printStackTrace();
            return GenericResponse.getFailureResponse(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponse.getFailureResponse(
                    "Something went wrong, contact admin!",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PostMapping("/NewAppointment")
    public ResponseEntity<GenericResponse> NewAppointment(
            @CookieValue(name = "SESSION_ID", required = false) String sessionId,
            @RequestParam String Title,
            @RequestParam String Description,
            @RequestParam String Appointment_Date,
            @RequestParam int DoctorID

    ) {
        try {
            Authentication auth = personService.getAuthentication(authRepo, sessionId);
            Person person = personService.getAuthorizedPatient(repository, auth);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Illness illness = new Illness(0, Title, Description, new Date());
            illness = illnessRepo.save(illness);
            Appointment appointment = new Appointment(0, auth.getPersonId(), DoctorID, illness.getIllnessId(), formatter.parse(Appointment_Date),
                    0, 0, 1, new Date());
            appointment = appointmentRepo.save(appointment);
            List<Object> objects = new ArrayList<>();
            objects.add(illness);
            objects.add(appointment);
            return GenericResponse.getSuccessResponse(objects);
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        } catch (InvalidSessionException | UnauthorizedException e) {
            e.printStackTrace();
            return GenericResponse.getFailureResponse(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponse.getFailureResponse(
                    "Something went wrong, contact admin!",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        return GenericResponse.getFailureResponse(
                "New Appointment is not done",
                HttpStatus.BAD_REQUEST
        );
    }

    @RequestMapping("/Patientdetails")
    public ResponseEntity<GenericResponse> personInfo(@CookieValue(name = "SESSION_ID", required = false) String sessionId) {
        try {
            Authentication auth = personService.getAuthentication(authRepo, sessionId);
            Person person = personService.getAuthorizedPatient(repository, auth);
            return GenericResponse.getSuccessResponse(personService.getPatientProfile(sessionId));
        } catch (InvalidSessionException | UnauthorizedException e) {
            e.printStackTrace();
            return GenericResponse.getFailureResponse(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponse.getFailureResponse(
                    "Something went wrong, contact admin!",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }


    @RequestMapping("/Doctordetails")
    public ResponseEntity<GenericResponse> DoctorInfo(@CookieValue(name = "SESSION_ID", required = false) String sessionId) {
        try {
            Authentication auth = personService.getAuthentication(authRepo, sessionId);
            Person person = personService.getAuthorizedDoctor(repository, auth);
            return GenericResponse.getSuccessResponse(personService.getDoctorProfile(sessionId));
        } catch (InvalidSessionException | UnauthorizedException e) {
            e.printStackTrace();
            return GenericResponse.getFailureResponse(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponse.getFailureResponse(
                    "Something went wrong, contact admin!",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }


    @RequestMapping("/users")
    public ResponseEntity<GenericResponse> getAllUsers(
            @CookieValue(name = "SESSION_ID", required = false) String sessionId
    ) {
        try {
            Authentication auth = personService.getAuthentication(authRepo, sessionId);
            Person person = personService.getAuthorizedAdmin(repository, auth);
            return GenericResponse.getSuccessResponse(repository.findAll());
        } catch (InvalidSessionException | UnauthorizedException e) {
            e.printStackTrace();
            return GenericResponse.getFailureResponse(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponse.getFailureResponse(
                    "Something went wrong, contact admin!",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @RequestMapping("/doctors")
    public ResponseEntity<GenericResponse> getAllDoctors(
            @CookieValue(name = "SESSION_ID", required = false) String sessionId
    ) {
        try {
            Authentication auth = personService.getAuthentication(authRepo, sessionId);
            Person person = personService.getAuthorizedAdmin(repository, auth);
            Iterable<Person> doctors = repository.findAllDoctors();
            return GenericResponse.getSuccessResponse(doctors);
        } catch (InvalidSessionException | UnauthorizedException e) {
            e.printStackTrace();
            return GenericResponse.getFailureResponse(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponse.getFailureResponse(
                    "Something went wrong, contact admin!",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

    }

    @RequestMapping("/PatientsNew")
    public ResponseEntity<GenericResponse> getAllPatients(
            @CookieValue(name = "SESSION_ID", required = false) String sessionId
    ) {
        try {
            Authentication auth = personService.getAuthentication(authRepo, sessionId);
            Person person = personService.getAuthorizedAdmin(repository, auth);
            Iterable<Person> patients = repository.findAllPatients();
            return GenericResponse.getSuccessResponse(patients);
        } catch (InvalidSessionException | UnauthorizedException e) {
            e.printStackTrace();
            return GenericResponse.getFailureResponse(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponse.getFailureResponse(
                    "Something went wrong, contact admin!",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }


    @PostMapping("/signUpDoctor")
    public ResponseEntity<GenericResponse> signUpDoctor(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam long phone,
            @RequestParam String dob,
            @RequestParam(required = false) String userName,
            @RequestParam String password,
            @RequestParam String serviceStartDate,
            @RequestParam(required = false, defaultValue = "500") int doctorCommission,
            @RequestParam int specializationId
    ) {
        String error;
        HttpStatus status;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Role userRole = roleRepo.findByRoleName("DOCTOR");
            Person person = savePerson(name, email, phone, dob, userName, password, formatter, userRole);
            Specialization specialization = specialRepo.findById(specializationId).orElse(null);
            if (specialization == null) {
                throw new IllegalArgumentException("Specialization not found with id " + specializationId);
            }
            Doctor doctor = new Doctor(person.getId(), formatter.parse(serviceStartDate), 0, specialization);
            Doctor finalDoctor = docRepo.save(doctor);
            Person finalPerson = person;
            DoctorCommission commission = new DoctorCommission(0, person.getId(), doctorCommission);
            DoctorCommission finalCommission = commissionRepo.save(commission);
            return GenericResponse.getSuccessResponse(new Object() {
                public final Person person = finalPerson;
                public final Doctor doctor = finalDoctor;
                public final DoctorCommission doctorCommission = finalCommission;
            });
        } catch (ParseException parseException) {
            error = "Unknown date format! Date format should be dd-MM-yyyy";
            status = HttpStatus.BAD_REQUEST;
            parseException.printStackTrace();
        } catch (UnsupportedOperationException e) {
            error = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
            e.printStackTrace();
        } catch (Exception e) {
            error = "Could not create account! Please contact admin";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            e.printStackTrace();
        }
        return GenericResponse.getFailureResponse(error, status);
    }

    @RequestMapping("/appointmentPatientHistory")
    public ResponseEntity<GenericResponse> getAppointmentPatientHistory(
            @CookieValue("SESSION_ID") String sessionId,
            @RequestParam int appointmentId
    ) {
        try {
            Authentication auth = personService.getAuthentication(authRepo, sessionId);
            Person person = personService.getAuthorizedDoctor(repository, auth);
            Appointment appointment = appointmentRepo.findById(appointmentId).orElse(null);
            if (appointment == null || appointment.getDoctor() != person.getId()) {
                return GenericResponse.getFailureResponse(
                        "Appointment does not exist or its not assigned to current user!",
                        HttpStatus.UNAUTHORIZED
                );
            }
            Iterable<Appointment> appointments = appointmentRepo.findAppointmentByPatientTillDate(
                    appointment.getPatient(),
                    appointment.getTimestamp()
            );

            ArrayList<PatientHistory> history = new ArrayList<>();
            for (Appointment app : appointments) {
                PatientHistory ph = new PatientHistory();
                ph.setAppointment(app);
                ph.setIllness(illnessRepo.findById(app.getIllness()).get());
                ph.setPrescription(presRepo.findByAppointmentId(app.getId()));
                history.add(ph);
            }
            return GenericResponse.getSuccessResponse(history);
        } catch (InvalidSessionException | UnauthorizedException e) {
            e.printStackTrace();
            return GenericResponse.getFailureResponse(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponse.getFailureResponse(
                    "Something went wrong, contact admin!",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @RequestMapping("/givePrescription")
    public ResponseEntity<GenericResponse> givePrescription(
            @CookieValue("SESSION_ID") String sessionId,
            @RequestParam int appointmentId,
            @RequestParam String description,
            @RequestParam int duration
    ) {
        try {
            Authentication auth = personService.getAuthentication(authRepo, sessionId);
            Person person = personService.getAuthorizedDoctor(repository, auth);
            Appointment appointment = appointmentRepo.findById(appointmentId).orElse(null);
            if (appointment == null || appointment.getDoctor() != person.getId()) {
                return GenericResponse.getFailureResponse(
                        "Appointment not found or you are not authorized",
                        HttpStatus.FORBIDDEN
                );
            }
            Prescription prescription = new Prescription();
            prescription.setAppointmentId(appointmentId);
            prescription.setCourseDuration(duration);
            prescription.setDescription(description);
            prescription = presRepo.save(prescription);
            appointment.setDoctorAccept(1);
            Person patient = repository.findById(appointment.getPatient()).get();
            Person doctor = repository.findById(appointment.getDoctor()).get();
            int fees = commissionRepo.findByDoctorId(doctor.getId()).getFees();
            PeopleWallet patientWallet = new PeopleWallet();
            patientWallet.setPersonId(patient.getId());
            patientWallet.setBalance(walletRepo.findById(patient.getId()).get().getBalance() - fees);
            walletRepo.save(patientWallet);
            PeopleWallet doctorWallet = new PeopleWallet();
            doctorWallet.setPersonId(doctor.getId());
            doctorWallet.setBalance(walletRepo.findById(doctor.getId()).get().getBalance() + fees);
            walletRepo.save(doctorWallet);
            appointmentRepo.save(appointment);
            return GenericResponse.getSuccessResponse(prescription);
        } catch (InvalidSessionException | UnauthorizedException e) {
            e.printStackTrace();
            return GenericResponse.getFailureResponse(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponse.getFailureResponse(
                    "Something went wrong, contact admin!",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }*/


    @PostMapping("/signUpUser")
    public ResponseEntity<GenericResponse> signUp(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String dob,
            @RequestParam String address,
            @RequestParam String password
    ) {
        String error;
        HttpStatus status;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            RoleEntity userRole = roleRepo.findRoleEntityByRoleName("USER");
            PersonEntity person = savePerson(name, email, phone, dob, password, address,formatter,userRole);
            return GenericResponse.getSuccessResponse(person);
        } catch (ParseException parseException) {
            error = "Unknown date format! Date format should be dd-MM-yyyy";
            status = HttpStatus.BAD_REQUEST;
            parseException.printStackTrace();
        } catch (UnsupportedOperationException e) {
            error = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
            e.printStackTrace();
        } catch (Exception e) {
            error = "Could not create account! Please contact admin";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            e.printStackTrace();
        }
        return GenericResponse.getFailureResponse(error, status);
    }

    private PersonEntity savePerson(@RequestParam String name, @RequestParam String email, @RequestParam String phone, @RequestParam String dob, @RequestParam String password,@RequestParam String address, SimpleDateFormat formatter,RoleEntity userRole) throws ParseException {
        if (repository.findByEmail(email) != null) {
            throw new UnsupportedOperationException("User with email id already exists!");
        }
        PersonEntity person = new PersonEntity(0, name, email, phone, formatter.parse(dob),password,2000,address,userRole.getRoleId());
         return repository.save(person);

    }


    @PostMapping("/signUpDoctor")
    public ResponseEntity<GenericResponse> signUpDoctor(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String dob,
            @RequestParam String address,
            @RequestParam int specializationId,
            @RequestParam int doctorFees,
            @RequestParam String password
    ) {
        String error;
        HttpStatus status;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            RoleEntity userRole = roleRepo.findRoleEntityByRoleName("DOCTOR");
            PersonEntity person = savePerson(name, email, phone, dob, password, address,formatter,userRole);
            SpecializationEntity specialization = specialRepo.findById(specializationId).orElse(null);
            if (specialization == null) {
                throw new IllegalArgumentException("Specialization not found with id " + specializationId);
            }
            DoctorEntity doctor = new DoctorEntity();
            doctor.setdId(person.getpId());
            doctor.setFees(doctorFees);
            ArrayList<SpecializationEntity> arrayspecialization = new ArrayList<>();
            arrayspecialization.add(specialization);
            doctor.setSpecializations(arrayspecialization);
            DoctorEntity finaldoctor = docRepo.save(doctor);
            finaldoctor.setPersonByDId(person);
            return GenericResponse.getSuccessResponse(new Object(){
                public final DoctorEntity doctor = finaldoctor;

            });
        } catch (ParseException parseException) {
            error = "Unknown date format! Date format should be dd-MM-yyyy";
            status = HttpStatus.BAD_REQUEST;
            parseException.printStackTrace();
        } catch (UnsupportedOperationException e) {
            error = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
            e.printStackTrace();
        } catch (Exception e) {
            error = "Could not create account! Please contact admin";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            e.printStackTrace();
        }
        return GenericResponse.getFailureResponse(error, status);
    }



    @PostMapping("/login")
    public ResponseEntity<GenericResponse> login(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        String error;
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            if (email.equals("") || password.equals("")) {
                error = "Insufficient data";
                throw new IllegalArgumentException(error);
            }
            PersonEntity person = repository.findByEmail(email);
            if (person == null) {
                error = "Incorrect email or password";
                throw new IllegalArgumentException(error);
            } else {
                if (!person.getPassword().equals(password)) {
                    error = "Incorrect email or password";
                    throw new IllegalArgumentException(error);
                }
                String fullString = person.getpId() + person.getEmail() + System.nanoTime();
                String sessionId = Base64.getEncoder().encodeToString(
                        MessageDigest.getInstance("MD5").digest(fullString.getBytes())
                );
                Cookie cookie = new Cookie("SESSION_ID", sessionId);
                cookie.setMaxAge(30 * 60);
                response.addCookie(cookie);
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.MINUTE, 30);
                Date expiryDate = calendar.getTime();
                SessionEntity session = new SessionEntity();
                session.setpId(person.getpId());
                session.setSessionId(sessionId);
                session.setTimestamp(expiryDate);
                authRepo.save(session);
                return GenericResponse.getSuccessResponse(new Object() {
                    public final SessionEntity auth = session;
                    public final RoleEntity role = person.getRole();
        });
    }
        } catch (NoSuchAlgorithmException e) {
            error = "Internal error! Contact Admin";
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
            e.printStackTrace();
        } catch (Exception e) {
            error = "Something went wrong! Contact admin";
            e.printStackTrace();
        }
        return GenericResponse.getFailureResponse(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    /*@RequestMapping("giveFeedback")
    public ResponseEntity<GenericResponse> giveFeedback(
            @CookieValue("SESSION_ID") String sessionId,
            @RequestParam int appointmentId,
            @RequestParam int rating,
            @RequestParam String description
    ) {
        try {
            Authentication auth = personService.getAuthentication(authRepo, sessionId);
            Person person = personService.getAuthorizedPatient(repository, auth);
            Appointment appointment = appointmentRepo.findById(appointmentId).orElse(null);
            if (appointment == null || appointment.getPatient() != person.getId()) {
                return GenericResponse.getFailureResponse(
                        "You are not authorized to do this",
                        HttpStatus.FORBIDDEN
                );
            }
            Feedback feedback = new Feedback();
            feedback.setAppointment(appointment);
            feedback.setRating(rating);
            feedback.setDescription(description);
            feedback = feedbackRepo.save(feedback);
            return GenericResponse.getSuccessResponse(feedback);
        } catch (InvalidSessionException | UnauthorizedException e) {
            e.printStackTrace();
            return GenericResponse.getFailureResponse(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponse.getFailureResponse(
                    "Something went wrong, contact admin!",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @RequestMapping("/logout")
    ResponseEntity<GenericResponse> logout(
            @CookieValue("SESSION_ID") String sessionId,
            HttpServletResponse response) {
        if (sessionId == null) {
            return GenericResponse.getFailureResponse("Session not found!", HttpStatus.UNAUTHORIZED);
        }
        Authentication auth = authRepo.getAuthenticationBySessionId(sessionId);
        auth.setSessionId(null);
        auth.setSessionEnd(Calendar.getInstance().getTime());
        authRepo.save(auth);
        Cookie cookie = new Cookie("SESSION_ID", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return GenericResponse.getSuccessResponse("Logout Successful");
    }*/
}
