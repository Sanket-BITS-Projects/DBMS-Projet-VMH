package assignment.virtualmedicalhome.vmh.controller;

import assignment.virtualmedicalhome.vmh.model.*;
import assignment.virtualmedicalhome.vmh.repository.*;
import assignment.virtualmedicalhome.vmh.response.GenericResponse;
import assignment.virtualmedicalhome.vmh.response.InvalidSessionException;
import assignment.virtualmedicalhome.vmh.response.UnauthorizedException;
import assignment.virtualmedicalhome.vmh.services.AppointmentService;
import assignment.virtualmedicalhome.vmh.services.PersonService;
import jdk.nashorn.internal.runtime.Specialization;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
import java.util.Optional;

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
        this.specialRepo = specialRepo;
        this.appointmentService = appointmentService;
        this.docRepo = docRepo;
    }

    @GetMapping("/test")
    public ResponseEntity<GenericResponse> test() {
        return GenericResponse.getSuccessResponse("Good to go");
    }

    @PostMapping("/SearchDoctor")
    public ResponseEntity<GenericResponse> searchDoctor(@CookieValue(name = "SESSION_ID", required = false) String sessionId,
                                                        @RequestParam String spName) {
        try {
            authRepo.getSessionEntityBySessionId(sessionId)
                    .orElseThrow(InvalidSessionException::new);
            SpecializationEntity specialization = specialRepo.getSpecializationEntityBySpeciality(spName);
            if (specialization == null) {
                return GenericResponse.getFailureResponse("Specialization not found", HttpStatus.BAD_REQUEST);
            }
            return GenericResponse.getSuccessResponse(specialization.getDoctorsBySpId());
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

    @RequestMapping("/PatientAppointments")
    public ResponseEntity<GenericResponse> patientAppointmentList(@CookieValue(name = "SESSION_ID", required = false) String sessionId) {
        try {
            SessionEntity session = authRepo.getSessionEntityBySessionId(sessionId)
                    .orElseThrow(InvalidSessionException::new);

            PersonEntity person = session.getPerson();
            return GenericResponse.getSuccessResponse(person.getAppointmentsByPId());
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

    @RequestMapping("/PatientAppointmentsForAdmin")
    public ResponseEntity<GenericResponse> patientAppointmentListForAdmin(@CookieValue(name = "SESSION_ID", required = false) String sessionId,
                                                                          @RequestParam int patientId) {
        try {
            SessionEntity session = authRepo.getSessionEntityBySessionId(sessionId)
                    .orElseThrow(InvalidSessionException::new);
            if (session.getPerson().getRole().getRoleId() != 1) {
                throw new UnauthorizedException("Admin can only access this data");
            }
            Optional<PersonEntity> oPersonEntity = repository.findById(patientId);
            if (oPersonEntity.isPresent()) {
                PersonEntity person = oPersonEntity.get();
                return GenericResponse.getSuccessResponse(person.getAppointmentsByPId());
            } else {
                return GenericResponse.getFailureResponse("Patient with given id not found", HttpStatus.BAD_REQUEST);
            }
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

    /*
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
    }*/

    @RequestMapping("/DoctorAppointments")
    public ResponseEntity<GenericResponse> doctorAppointmentList(@CookieValue(name = "SESSION_ID", required = false) String sessionId) {
        try {
            SessionEntity session = authRepo.getSessionEntityBySessionId(sessionId)
                    .orElseThrow(InvalidSessionException::new);

            PersonEntity person = session.getPerson();
            if (person.getRoleId() != 2) {
                throw new UnauthorizedException("Only doctor can access this data");
            }
            DoctorEntity doctor = person.getDoctorByPId();
            return GenericResponse.getSuccessResponse(doctor.getAppointmentsByDId());
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

    @RequestMapping("/DoctorAppointmentsForAdmin")
    public ResponseEntity<GenericResponse> doctorAppointmentListForAdmin(@CookieValue(name = "SESSION_ID", required = false) String sessionId,
                                                                         @RequestParam int doctorId) {
        try {
            SessionEntity session = authRepo.getSessionEntityBySessionId(sessionId)
                    .orElseThrow(InvalidSessionException::new);

            if (session.getPerson().getRoleId() != 1) {
                throw new UnauthorizedException("Only admin can access this data");
            }

            Optional<PersonEntity> oPersonEntity = repository.findById(doctorId);
            PersonEntity person;
            if (!oPersonEntity.isPresent() || (person = oPersonEntity.get()).getRoleId() != 2) {
                return GenericResponse.getFailureResponse("Doctor with given id not found", HttpStatus.BAD_REQUEST);
            } else {
                return GenericResponse.getSuccessResponse(person.getDoctorByPId().getAppointmentsByDId());
            }
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

    /*@RequestMapping("/DoctorSchedule")
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

    @PostMapping("/signUpUser")
    public ResponseEntity<GenericResponse> signUp(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam long phone,
            @RequestParam String dob,
            @RequestParam(required = false) String userName,
            @RequestParam String password
    ) {
        String error;
        HttpStatus status;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            RoleEntity userRole = roleRepo.findRoleEntityByRoleName("USER");

            PersonEntity person = savePerson(name, email, phone, dob, userName, password, formatter, userRole);
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

    private Person savePerson(@RequestParam String name, @RequestParam String email, @RequestParam long phone, @RequestParam String dob, @RequestParam(required = false) String userName, @RequestParam String password, SimpleDateFormat formatter, Role userRole) throws ParseException {
        if (repository.findByEmail(email) != null) {
            throw new UnsupportedOperationException("User with email id already exists!");
        }
        Person person = new Person(0, name, email, phone, formatter.parse(dob), userRole);
        person = repository.save(person);
        Authentication auth = new Authentication();
        auth.setPersonId(person.getId());
        auth.setUsername(userName);
        auth.setPassword(password);
        authRepo.save(auth);
        repository.addBalance(person.getId(), 2000);
        return person;
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
    public ResponseEntity<GenericResponse> login(HttpServletRequest request, HttpServletResponse response) {
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
                SessionEntity sessionEntity = new SessionEntity();
                sessionEntity.setpId(person.getpId());
                sessionEntity.setSessionId(sessionId);
                sessionEntity.setTimestamp(expiryDate);
                sessionEntity = authRepo.save(sessionEntity);
                sessionEntity.setPerson(person);
                return GenericResponse.getSuccessResponse(sessionEntity);
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

    @RequestMapping("/logout")
    ResponseEntity<GenericResponse> logout(
            @CookieValue("SESSION_ID") String sessionId,
            HttpServletResponse response) {
        Optional<SessionEntity> oSession;
        if (sessionId == null || !(oSession = authRepo.getSessionEntityBySessionId(sessionId)).isPresent()) {
            return GenericResponse.getFailureResponse("Session not found!", HttpStatus.UNAUTHORIZED);
        } else {
            SessionEntity session = oSession.get();
            authRepo.delete(session);
        }
        Cookie cookie = new Cookie("SESSION_ID", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return GenericResponse.getSuccessResponse("Logout Successful");
    }
}
