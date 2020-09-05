package com.capgemini.loanpecunia;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.capgemini.loanpecunia.dao.LoanRequestDao;
import com.capgemini.loanpecunia.entities.LoanDisbursal;
import com.capgemini.loanpecunia.entities.LoanRequests;
import com.capgemini.loanpecunia.service.LoanPecuniaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoanPecuniaApplicationTests {

	@Autowired 
	LoanPecuniaService service;
	
	@MockBean
	LoanRequestDao rdao;
	
	
	
	@Test
	public void loanRequestTest() {
		LoanRequests loanreq = new LoanRequests(2,"22222222223",5000,25,900,2,"pending","study");
		assertEquals("Your Loan Request is successful", service.loanRequest(loanreq));
	}
	
	@Test
	public void loanRequestTestMock() {
		LoanRequests loanreq = new LoanRequests(5,"22222222224",6000,29,570,5,"pending","car loan");
		when(rdao.save(loanreq)).thenReturn(loanreq);
		assertEquals(loanreq, rdao.save(loanreq));
	}
	
	
	@Test
	public void allApprovedTest() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:8089/loan/approvedrequests/222222222224";
		URI uri = new URI(baseUrl);
		ResponseEntity<LoanDisbursal> result = restTemplate.getForEntity(uri, LoanDisbursal.class);
		assertEquals(200, result.getStatusCodeValue());
	}
	
	@Test
	public void allRejectedTest() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:8089/loan/rejectedrequests/222222222224";
		URI uri = new URI(baseUrl);
		ResponseEntity<LoanDisbursal> result = restTemplate.getForEntity(uri, LoanDisbursal.class);
		assertEquals(200, result.getStatusCodeValue());
	}
	
	
	@SuppressWarnings("rawtypes")
	@Test
	public void allRequestsTest() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:8089/loan/getAllRequests";
		URI uri = new URI(baseUrl);

		ResponseEntity<List> result = restTemplate.exchange(uri, HttpMethod.GET, null, List.class);
		assertEquals(200, result.getStatusCodeValue());
	}
	
}
