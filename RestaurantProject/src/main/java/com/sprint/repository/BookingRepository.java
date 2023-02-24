package com.sprint.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sprint.models.Booking;


@Repository
public interface BookingRepository extends JpaRepository<Booking,Long>{
	List<Booking> findByDate(@Param("startDate")LocalDate date);
	
	@Query("SELECT date, numberOfGuests FROM Booking b WHERE b.date = :startDate and b.numberOfGuests =:guests ")
	Optional<Booking> findByDateAndNumberOfGuests(@Param("startDate")LocalDate date,@Param("guests")int numberOfGuests);

}
