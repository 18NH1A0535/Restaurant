package com.sprint.controllers;

import java.time.LocalDate;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.dto.TransactionDTO;
//import com.sprint.exceptions.BookingNotFoundException;
import com.sprint.exceptions.CustomerNotFoundException;
import com.sprint.exceptions.InvalidCredentialsException;
import com.sprint.exceptions.TransactionRecordNotFoundException;
import com.sprint.models.Admin;
import com.sprint.models.Booking;
import com.sprint.models.Customer;
import com.sprint.models.Restaurant;
import com.sprint.repository.TransactionRepository;
import com.sprint.service.AdminImpl;
import com.sprint.service.BookingImpl;
import com.sprint.service.CustomerImpl;
import com.sprint.service.RestaurantService;
import com.sprint.service.TransactionService;



@RestController
@RequestMapping("/admin")
public class AdminController {
	  
	  @Autowired
	  private TransactionRepository transactionRepository;
	  
	  
	  @Autowired
	  AdminImpl adminImpl;
	 
	  @Autowired
	  private CustomerImpl customerImpl;
	  
	  @Autowired
	   private BookingImpl bookingImpl;
	 
	  @Autowired
	   TransactionService transactionService;
	  
	  @Autowired
	  RestaurantService restaurantService;
	  
	  @Autowired
	   public AdminController(CustomerImpl customerImpl) {
	      this.customerImpl = customerImpl;
	   }
	   public AdminController(BookingImpl bookingImpl) {
	      this.bookingImpl = bookingImpl;
	   }

	   public AdminController(TransactionService transactionService) {
		      this.transactionService = transactionService;
		   }
	   
	   //admin registration
	   @PostMapping("/register")
		public ResponseEntity registerAdmin(@RequestBody Admin admin) {
		   Admin f=this.adminImpl.registerAdmin(admin);
			return new ResponseEntity(f, HttpStatus.CREATED);
		}
	   
	   //admin login
	   @PostMapping("/loginAdmin/{email}/{password}")
	   public String loginAdmin(@PathVariable String email,String password) throws InvalidCredentialsException{
		   return this.adminImpl.loginAdmin(email,password);
		   }
	
	   
	// Display a list of customers ordered by frequency of visits
	   @GetMapping(value="/customers/visits")
	   public List<Customer> getCustomersByFrequencyOfVisits(Model model) {
	      List<Customer> customers = customerImpl.findCustomersByFrequencyOfVisits();

	      model.addAttribute("customers", customers);

	      return customers;
	   }
	      // display list of bookings for a given  date
	      @GetMapping(value="/customers")
	      public List<Booking> getBookings(@RequestParam("date")@DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date,
	    		  Model model) throws TransactionRecordNotFoundException{
	       List<Booking> bookings = bookingImpl.findBookingByDate(date);

	         model.addAttribute("bookings", bookings);

	         return bookings;
	      
	   }
	  
	  //amount spent btw dates
	  @GetMapping(value="/{id}/amountSpentOnDate")
	  public Double getTotalAmountSpentByCustomerIdBetweenDates(@PathVariable("id") Long customerId,
			  													@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
	                                                            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate
	                                                             ) {
	    return transactionRepository.findTotalAmountSpentByCustomerIdBetweenDates(customerId, endDate,startDate);
	  }
	  
//	  @GetMapping(value="/bookings")
//	  public double getDiscounts(@RequestParam("customerId") Long customerId,Model model) throws CustomerNotFoundException {
//	    
//		  double discount;
//		  discount=adminImpl.discountsForCustomers(customerId);
//	      model.addAttribute("discount", discount);
//	      return discount;
//	    }
	  
	  //total money spent
	  @GetMapping(value="/{id}")
	  public Double getCalculateMoneySpent(@PathVariable("id") Long customerId) throws CustomerNotFoundException
	  {
		  return adminImpl.calculateMoneySpent(customerId);
	  }
	  
	   //to post transaction
	  @PostMapping(value="/{bookingId}/transaction")
	  public ResponseEntity postTransaction(@PathVariable("bookingId")long bookingId,@RequestBody TransactionDTO transaction)
	  {
	  TransactionDTO tr=this.transactionService.createRecord(bookingId,transaction);
		  return new ResponseEntity(tr,HttpStatus.CREATED);
	  }
	  
	  @PostMapping(value="/restaurant")
	  public ResponseEntity postRestaurant(@RequestBody Restaurant restaurant)
	  {
		  Restaurant res=this.restaurantService.createRestaurant(restaurant);
		  return new ResponseEntity(res,HttpStatus.CREATED);
	  }

	}

