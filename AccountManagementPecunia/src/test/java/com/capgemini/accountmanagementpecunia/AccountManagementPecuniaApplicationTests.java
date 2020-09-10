package com.capgemini.accountmanagementpecunia;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import org.springframework.web.client.RestTemplate;

import com.capgemini.accountmanagementpecunia.entities.Account;
import com.capgemini.accountmanagementpecunia.entities.Customer;


@SpringBootTest
public class AccountManagementPecuniaApplicationTests {

	@Test
	public void addAccount() throws URISyntaxException {
		
		RestTemplate restTemplate = new RestTemplate();
	     
	    final String baseUrl = "http://localhost:" +8098+ "/AccountManagement/create";
	    URI uri = new URI(baseUrl);
	    
	    Customer customer=new Customer();
		Account account=new Account();
		account.setAccountId("222222222223");
		account.setAccountType("savings");
		account.setBranch("nagole");
		account.setAmount((long) 3000);
		customer.setAadharNumber("258963147753");
		customer.setCustomerName("Karthik");
		customer.setContactNumber("7659873099");
		customer.setPanNumber("AAAPZ1234C");
		customer.setDateOfBirth("1998-10-26");
		customer.setGender("male");
		customer.setAddress("Khammam");
		customer.setAccount(account);
		
		HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "Account created successfully");      
        HttpEntity<Customer> request = new HttpEntity<>(customer, headers);
        
        ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());
	    Assert.assertNotNull(customer);
	}
	
	@Test
	public void showAccountDetailsTest() throws URISyntaxException {
		RestTemplate restTemplate=new RestTemplate();
		final String Url= "http://localhost:" +8098+ "/AccountManagement/find/222222222224";
		URI uri=new URI(Url);
		 ResponseEntity<Customer> result = restTemplate.getForEntity(uri, Customer.class);
		    Customer customer = result.getBody();
		    Assert.assertEquals(200, result.getStatusCodeValue());
		    Assert.assertNotNull(customer);
	}
	
	
	
	@Test
	public void deleteAccountTest() throws URISyntaxException {
		RestTemplate restTemplate=new RestTemplate();
		final String Url= "http://localhost:" +8098+ "/AccountManagement/delete/222222222223";
		URI uri=new URI(Url);
		ResponseEntity<String> result=restTemplate.exchange(uri,HttpMethod.DELETE,null,String.class);
		 Assert.assertEquals(200, result.getStatusCodeValue());
	}
	

}
