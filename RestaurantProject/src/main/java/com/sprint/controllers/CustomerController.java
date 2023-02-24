package com.sprint.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.dto.BookingDTO;
import com.sprint.dto.CustomerDTO;
import com.sprint.exceptions.BookingAlreadyExistsException;
import com.sprint.exceptions.BookingNotFoundException;
import com.sprint.exceptions.CustomerAlreadyExistsException;
import com.sprint.exceptions.CustomerNotFoundException;
import com.sprint.exceptions.InvalidCredentialsException;
import com.sprint.models.Booking;
import com.sprint.models.Customer;
import com.sprint.repository.CustomerRepository;
import com.sprint.service.BookingService;
import com.sprint.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private BookingService bookingService;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	private CustomerService customerService;
	  
		@Autowired
	  public CustomerController(BookingService bookingService) {
	    this.bookingService = bookingService;
	  }
		
		@PostMapping("/register")
		public ResponseEntity registerCustomer(@RequestBody CustomerDTO customer)throws CustomerAlreadyExistsException {
			CustomerDTO usr= this.customerService.registerCustomer(customer);
			return new ResponseEntity(usr, HttpStatus.CREATED);
		}
		
		@PostMapping("/loginUser/{email}/{password}")
		public String loginUser(@PathVariable String email,String password) throws InvalidCredentialsException{
			return this.customerService.loginUser(email,password);
			}
	
		@PostMapping(value="/{custId}/book")
		public BookingDTO bookTable(@PathVariable long custId,@RequestBody BookingDTO booking) throws BookingAlreadyExistsException
		{
			return bookingService.createBooking(custId,booking);
		}
	  
	  @PutMapping(value="/bookings/{bookingId}")
	  public ResponseEntity<Booking> updateBooking(@PathVariable Long bookingId, @RequestBody  LocalDate newDate) throws BookingNotFoundException{
		   Booking updatedBooking = null;
		
			updatedBooking = bookingService.updateBooking(bookingId, newDate);
		   return ResponseEntity.ok(updatedBooking);
	  }
	  
	  @DeleteMapping(value="/bookings/{bookingId}")
	  public ResponseEntity<Void> cancelBooking(@PathVariable Long bookingId)throws BookingNotFoundException {
	    
			bookingService.cancelBooking(bookingId);
	    return ResponseEntity.noContent().build();
	  }
	  
	  @GetMapping(value="/{id}")
	  public Customer getCustomerById(@PathVariable Long id) throws CustomerNotFoundException{
        return customerService.findCustomerById(id);
    } 
	  @GetMapping
	  public List<Customer> getAllCustomers(){
        return customerService.getCustomers();
    }  

}
