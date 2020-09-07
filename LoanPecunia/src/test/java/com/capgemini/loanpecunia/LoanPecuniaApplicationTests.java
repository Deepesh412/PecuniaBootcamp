package com.capgemini.loanpecunia;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.capgemini.loanpecunia.dao.LoanDisbursalDao;
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
	
	@MockBean
	LoanDisbursalDao ddao;
	
	
	
	@Test
	public void loanRequestTest() {
		LoanRequests loanreq = new LoanRequests(2,"22222222226",5000,25,900,2,"pending","study");
		assertEquals("Your Loan Request is successful", service.loanRequest(loanreq));
	}
	
	@Test
	public void loanRequestTestMock() {
		LoanRequests loanreq = new LoanRequests(5,"22222222224",6000,29,570,5,"pending","car loan");
		when(rdao.save(loanreq)).thenReturn(loanreq);
		assertEquals(loanreq, rdao.save(loanreq));
	}
	
	
	@Test
	public void loandisbursalTest() {

	
		Mockito.when(ddao.findAll()).thenReturn(Stream.of(new LoanDisbursal()).collect(Collectors.toList()));
		assertEquals(1, ddao.findAll().size());

	}
	
	
}
