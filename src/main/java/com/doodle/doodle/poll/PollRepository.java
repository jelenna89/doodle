package com.doodle.doodle.poll;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {

	List<Poll> findAll();
	 
	@Query(value = "select * from doodle.polls WHERE  poll -> 'initiator' ->> 'name' = :name", nativeQuery = true)
	List<Poll> findAll(@Param("name") String name);
	 
	 
	@Query(value = "select * from doodle.polls p \n" + 
	 		"where to_timestamp(( poll ->> 'initiated' )\\:\\:double precision/1000) >= :since ", nativeQuery = true)
	List<Poll> findAllSince(@Param("since") Date since);

	@Query(value = "select * from doodle.polls p \n" + 
	 		"where to_timestamp(( poll ->> 'initiated' )\\:\\:double precision/1000) >= :until ", nativeQuery = true)
	List<Poll> findAllUntil(@Param("until") Date until);

	 
	@Query(value = "select * from doodle.polls p \n" + 
			"	 where to_timestamp(( poll ->> 'initiated' )\\:\\:double precision/1000) >= :from \n" + 
			"	 and  to_timestamp(( poll ->> 'initiated' )\\:\\:double precision/1000) <= :to", nativeQuery = true)
	List<Poll> findAllBetween(@Param("from") Date from, @Param("to") Date to);
}
