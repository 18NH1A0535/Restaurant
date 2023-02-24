package com.sprint.service;


import java.time.LocalDate;

import java.util.List;
import com.sprint.exceptions.BookingNotFoundException;
import com.sprint.exceptions.TransactionRecordNotFoundException;
import com.sprint.dto.BookingDTO;
import com.sprint.exceptions.BookingAlreadyExistsException;
import com.sprint.models.Booking;

public interface BookingService {
	List<Booking> findBookingByDate(LocalDate date)throws TransactionRecordNotFoundException;
	Booking updateBooking(long bookingId,LocalDate newDate) throws BookingNotFoundException;
	BookingDTO createBooking(long custId,BookingDTO bookingDTO) throws BookingAlreadyExistsException;
	public void cancelBooking(Long bookingId) throws BookingNotFoundException;
}