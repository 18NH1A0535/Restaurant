//package com.sprint;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.time.LocalDate;
//import java.util.Optional;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import static org.mockito.Mockito.*;
//import com.sprint.exceptions.BookingNotFoundException;
//import com.sprint.exceptions.CustomerNotFoundException;
//import com.sprint.models.Booking;
//import com.sprint.models.Customer;
//import com.sprint.repository.BookingRepository;
//import com.sprint.repository.CustomerRepository;
//import com.sprint.service.BookingImpl;
//import com.sprint.service.BookingService;
//import com.sprint.service.CustomerImpl;
//import static org.mockito.BDDMockito.willDoNothing;
//
//public class CustomerControllerTests {
//	@InjectMocks
//CustomerImpl customerService;
//	@Mock
//	CustomerRepository customerRepository;
//	@Mock
//	BookingImpl bookingService;
//	@Mock
//	BookingRepository bookingRepository;
//	@Mock
//	BookingService bookService;
//	private Long bookingId;
//	private Booking booking;
//	@Mock
//	private Customer customer;
//	
//	@Test 
//	public void testUpdateBooking() throws BookingNotFoundException {
//		// Create a mock booking object
//		Booking booking = Mockito.mock(Booking.class);
//		
//		// Set up the booking repository mock
//		BookingRepository bookingRepository = Mockito.mock(BookingRepository.class);
//		Mockito.when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));
//		
//		// Create a new date
//		LocalDate newDate = LocalDate.of(2023, 3, 1);
//		
//		// Call the updateBooking method
//		BookingImpl bookingService = new BookingImpl(bookingRepository);
//		bookingService.updateBooking(1L, newDate);
//		
//		// Verify that the booking date was updated and saved
//		Mockito.verify(booking).setDate(newDate);
//		Mockito.verify(bookingRepository).save(booking);
//		}
//
//	
//	@Test
//	  public void cancelBookingTest() throws BookingNotFoundException {
//	    // Create a mock object of the BookingRepository
//	    BookingRepository repository = mock(BookingRepository.class);
//
//	    // Define the behavior of the mock repository when findById method is called
//	    Booking booking = new Booking();
//	    when(repository.findById(anyLong())).thenReturn(Optional.of(booking));
//
//	    // Call the cancelBooking method of the BookingService class and pass the mock repository object
//	    BookingImpl service = new BookingImpl(repository);
//	    service.cancelBooking(1L);
//
//	    // Verify if the delete method was called on the mock repository
//	    verify(repository, times(1)).delete(booking);
//	  }
//
//	  @Test(expected = BookingNotFoundException.class)
//	  public void cancelBookingNotFoundTest() throws BookingNotFoundException {
//	    // Create a mock object of the BookingRepository
//	    BookingRepository repository = mock(BookingRepository.class);
//
//	    // Define the behavior of the mock repository when findById method is called
//	    when(repository.findById(anyLong())).thenReturn(Optional.empty());
//
//	    // Call the cancelBooking method of the BookingService class and pass the mock repository object
//	    BookingImpl service = new BookingImpl(repository);
//	    service.cancelBooking(1L);
//	  }
//
//
//	}
//
//
