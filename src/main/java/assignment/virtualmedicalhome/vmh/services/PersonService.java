package assignment.virtualmedicalhome.vmh.services;


import org.springframework.stereotype.Service;

@Service
public class PersonService {
   /* private final PersonRepository personRepository;
    private final SessionRepository authRepo;
    private final PeopleWalletRepository peopleWalletRepository;
    private final DoctorRepository doctorRepository;
    private final DoctorCommissionRepository doctorCommissionRepository;

    public PersonService(
            PersonRepository personRepository,
            SessionRepository authRepo,
            PeopleWalletRepository peopleWalletRepository,
            DoctorRepository doctorRepository,
            DoctorCommissionRepository doctorCommissionRepository
    ) {
        this.personRepository = personRepository;
        this.authRepo = authRepo;
        this.peopleWalletRepository = peopleWalletRepository;
        this.doctorRepository = doctorRepository;
        this.doctorCommissionRepository = doctorCommissionRepository;
    }

    public Collection<Object> getPatientProfile(String sessionId) {
        ArrayList<Object> objects = new ArrayList<>();
        Authentication auth = authRepo.getAuthenticationBySessionId(sessionId);
        Person person = personRepository.findById(auth.getPersonId()).get();
        PeopleWallet peopleWallet = peopleWalletRepository.findById(auth.getPersonId()).get();
        objects.add(person);
        objects.add(peopleWallet);
        return objects;
    }

    public Collection<Object> getDoctorProfile(String sessionId) {
        ArrayList<Object> objects = new ArrayList<>();
        Authentication auth = authRepo.getAuthenticationBySessionId(sessionId);
        Person person = personRepository.findById(auth.getPersonId()).get();
        Doctor doctor = doctorRepository.findById(auth.getPersonId()).get();
        DoctorCommission doctorCommission = doctorCommissionRepository.findByDoctorId(auth.getPersonId());
        PeopleWallet peopleWallet = peopleWalletRepository.findById(auth.getPersonId()).get();
        objects.add(person);
        objects.add(doctor);
        objects.add(peopleWallet);
        objects.add(doctorCommission);
        return objects;
    }


    public Authentication getAuthentication(
            SessionRepository authRepo,
            String sessionId
    ) throws InvalidSessionException {
        if (sessionId == null) throw new InvalidSessionException();
        Authentication auth = authRepo.getAuthenticationBySessionId(sessionId);
        if (auth == null || auth.getSessionEnd().before(Calendar.getInstance().getTime())) {
            throw new InvalidSessionException();
        }
        return auth;
    }

    public Person getAuthorizedPatient(
            PersonRepository repository,
            Authentication auth
    ) throws UnauthorizedException {
        Person p = getPersonIfRoleMatches(repository, auth, "USER");
        if (p == null) throw new UnauthorizedException("Only patient can see this page");
        else return p;
    }

    public Person getAuthorizedDoctor(
            PersonRepository repository,
            Authentication auth
    ) throws UnauthorizedException {
        Person p = getPersonIfRoleMatches(repository, auth, "DOCTOR");
        if (p == null) throw new UnauthorizedException("Only doctor can see this page");
        else return p;
    }

    public Person getAuthorizedAdmin(
            PersonRepository repository,
            Authentication auth
    ) throws UnauthorizedException {
        Person p = getPersonIfRoleMatches(repository, auth, "ADMIN");
        if (p == null) throw new UnauthorizedException("Only admin can see this page");
        else return p;
    }

    private Person getPersonIfRoleMatches(
            PersonRepository repository,
            Authentication auth,
            String role
    ) throws UnauthorizedException {
        if (auth == null) return null;
        Person person = repository.findById(auth.getPersonId()).orElse(null);
        if (person != null && person.getRole().getRoleName().equals(role)) {
            return person;
        }
        return null;
    }*/
}
