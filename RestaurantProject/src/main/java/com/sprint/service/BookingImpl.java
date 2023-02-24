package com.sprint.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.exceptions.BookingNotFoundException;
import com.sprint.exceptions.TransactionRecordNotFoundException;
import com.sprint.dto.BookingDTO;
import com.sprint.exceptions.BookingAlreadyExistsException;
import com.sprint.models.Admin;
import com.sprint.models.Booking;
import com.sprint.models.Customer;
import com.sprint.repository.BookingRepository;

@Service
public class BookingImpl implements BookingService
{
	private BookingRepository bookingRepository;
	@Autowired
	public BookingImpl(BookingRepository bookingRepository){
		this.bookingRepository = bookingRepository;
	}
	
	private Admin admin=new Admin();
	private Customer customer=new Customer();
	
	//allow a customer
	@Override
	public BookingDTO createBooking(long custId,BookingDTO booking)throws BookingAlreadyExistsException {
		Optional<Booking> existingBooking = bookingRepository.findByDateAndNumberOfGuests(booking.getDate(), booking.getNumberOfGuests());
		Booking book;
		if(existingBooking.isPresent()) {
			throw new BookingAlreadyExistsException("Table is already booked for given date and party size");
			}
		else
		{
		admin.setAdminId(1);
		customer.setCustomerId(custId);
		book = new Booking();
		book.setId(booking.getId());
		book.setDate(booking.getDate());
		book.setTime(LocalTime.now());
		book.setAdmin(admin);
		book.setCustomer(customer);
		book.setNumberOfGuests(booking.getNumberOfGuests());
		
		}
		Booking b=bookingRepository.save(book);
		booking.setId(book.getId());
		return booking;
	}

	
	public List<Booking> findBookingByDate(LocalDate date)throws TransactionRecordNotFoundException{
		List<Booking>  existBooking = bookingRepository.findByDate(date);
		if(existBooking.isEmpty()) {
			throw new TransactionRecordNotFoundException("Booking records are not found for given date ");
		}
		else {
			return bookingRepository.findByDate(date);
		}
}
	

	
	@Override
	public Booking updateBooking(long bookingId, LocalDate newDate) throws BookingNotFoundException 
	{
	
	      Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + bookingId));
	      booking.setDate(newDate);
	      return bookingRepository.save(booking);
	}


	@Override
	public void cancelBooking(Long bookingId) throws BookingNotFoundException {
		Booking booking = bookingRepository.findById(bookingId)
			      .orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + bookingId));
			    bookingRepository.delete(booking);
		
	}

	
	}


