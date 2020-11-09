package assignment.virtualmedicalhome.vmh.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import assignment.virtualmedicalhome.vmh.model.Feedback;

@Repository
public interface FeedbackRepository extends CrudRepository<Feedback, Integer> {
	List<Feedback> findAll();
	List<Feedback>findByRating(int Rating);
	
 }
