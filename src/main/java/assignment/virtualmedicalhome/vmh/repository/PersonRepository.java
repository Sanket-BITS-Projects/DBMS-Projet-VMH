package assignment.virtualmedicalhome.vmh.repository;


import assignment.virtualmedicalhome.vmh.model.PersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, Integer> {
    PersonEntity findByEmail(String email);

    /*@Query(value = "select p from Person as p where p.role.roleId = 3")
    Iterable<Person> findAllDoctors();

    @Query(value = "select p from Person as p where p.role.roleId = 1")
    Iterable<Person> findAllPatients();

    @Modifying
    @Query(value = "insert into person_type(p_id,p_type) values (:personId, :role);"
            , nativeQuery = true)
    @Transactional
    void assignRole(@Param("personId") int id, @Param("role") int role);

    @Modifying
    @Query(value = "insert into PEOPLE_WALLET(p_id, balance) values ( :personId,:balance );",
            nativeQuery = true)
    @Transactional
    void addBalance(@Param("personId") int id, @Param("balance") int balance);

    @Modifying
    @Query(value = "update PeopleWallet set balance = :balance where personId = :id")
    @Transactional
    void updateBalance(int id, int balance);*/
}
