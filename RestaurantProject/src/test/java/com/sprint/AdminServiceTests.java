package com.sprint;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sprint.exceptions.CustomerNotFoundException;
import com.sprint.exceptions.InvalidCredentialsException;
import com.sprint.models.Admin;
import com.sprint.models.Customer;
import com.sprint.repository.AdminRepository;
import com.sprint.repository.CustomerRepository;
import com.sprint.repository.TransactionRepository;
import com.sprint.service.AdminImpl;
import com.sprint.service.TransactionImpl;


public class AdminServiceTests {

	@InjectMocks
	private AdminImpl adminImpl;
	@Mock
	private AdminRepository adminRepository;
	
	@Mock
    private TransactionRepository transactionRepository;
	@Mock
	private CustomerRepository customerRepository;
	@InjectMocks
    private TransactionImpl transactionImpl;
    
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		}
    
    @Test
    public void testRegisterAdmin() {
    	Admin admin = new Admin();
    	admin.setAdminId(1);
    	admin.setAdminName("abc");
		admin.setEmailId("abc@gmail.com");
		admin.setPassword("admin");
        when(adminRepository.save(admin)).thenReturn(admin);
        
        Admin result = adminImpl.registerAdmin(admin);
        
        assertEquals(admin, result);
        verify(adminRepository).save(admin);
    }
    
    @Test
    public void testLoginAdminWithValidCredentials() throws InvalidCredentialsException {
        String email = "abc@gmail.com";
        String password = "admin";
        when(adminRepository.validateAdmin(email, password)).thenReturn(new Admin());
        
        String result = adminImpl.loginAdmin(email, password);
        
        assertEquals("Login Successful", result);
        verify(adminRepository).validateAdmin(email, password);
    }
    
    @Test(expected = InvalidCredentialsException.class)
    public void testLoginAdminWithInvalidCredentials() throws InvalidCredentialsException {
        String email = "abc@gmail.com";
        String password = "admin";
        when(adminRepository.validateAdmin(email, password)).thenReturn(null);
        
        adminImpl.loginAdmin(email, password);
    }
                
        @Test
        public void testCalculateMoneySpentWithExistingCustomer() throws CustomerNotFoundException {
            long customerId = 1;
            Customer customer = new Customer();
            customer.setCustomerId(customerId);
            when(transactionImpl.findCustomerById(customerId)).thenReturn(customer);
            when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
            when(transactionRepository.findTotalCostSpent(customerId)).thenReturn(100.0);
            
            double result = adminImpl.calculateMoneySpent(customerId);
            		
            assertEquals(100.0, result, 0.001);
            verify(transactionImpl).findCustomerById(customerId);
            verify(transactionRepository).findTotalCostSpent(customerId);
        }
        
        @Test(expected = CustomerNotFoundException.class)
        public void testCalculateMoneySpentWithNonExistingCustomer() throws CustomerNotFoundException {
            long customerId = 456;
            when(transactionImpl.findCustomerById(customerId)).thenThrow(new CustomerNotFoundException("Customer not found"));
            
            adminImpl.calculateMoneySpent(customerId);
        }
        
    }
