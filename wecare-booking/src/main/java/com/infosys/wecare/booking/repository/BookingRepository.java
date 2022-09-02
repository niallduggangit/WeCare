package com.infosys.wecare.booking.repository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.infosys.wecare.booking.entities.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	@Query("SELECT b FROM Booking b WHERE b.userId=:userId AND b.appointmentDate >:today")
    List<Booking> findByUserIdAndAppointmentDateGreaterThanToday(@Param("userId")String userId, @Param("today")LocalDate today);
	
	@Query("SELECT b FROM Booking b WHERE b.coachId=:coachId AND b.appointmentDate >:today")
    List<Booking> findByCoachIdAndAppointmentDateGreaterThanToday(@Param("coachId")String coachId, @Param("today")LocalDate today);
	
    Optional<Booking> findByUserIdAndAppointmentDateAndSlot(String userId, LocalDate appointmentDate, Character slot);
}
