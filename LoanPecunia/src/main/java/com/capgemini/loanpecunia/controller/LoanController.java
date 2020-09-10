package com.capgemini.loanpecunia.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.capgemini.loanpecunia.entities.LoanDisbursal;
import com.capgemini.loanpecunia.entities.LoanRequests;
import com.capgemini.loanpecunia.exceptions.NullFoundException;
import com.capgemini.loanpecunia.service.LoanPecuniaService;

@RestController
@RequestMapping("/loan")
@CrossOrigin("http://localhost:4200")
public class LoanController {

	@Autowired
	LoanPecuniaService service;
	
	@PostMapping("/request")
	public ResponseEntity<String> loanRequest(@RequestBody LoanRequests loanreq)
	{
		if(loanreq.getAccountId()==null)
		{
			throw new NullFoundException("cannot be null");
		}
		else
		{
			String request = service.loanRequest(loanreq);
			return new ResponseEntity<String>(request, new HttpHeaders(), HttpStatus.OK); 
		}
	}
	
	@GetMapping("/getAllRequests")
	public ResponseEntity<List<LoanRequests>> getAllRequests(){
		ArrayList<LoanRequests> requests = service.getAllRequests();
		return new ResponseEntity<List<LoanRequests>>(requests, HttpStatus.OK);
	}
	
   @GetMapping("/approvedrequests")
	public ArrayList<LoanDisbursal> getApproveLoans(){
	   
			return (ArrayList<LoanDisbursal>)service.getApproveLoans();
		
	}
	
	
	@GetMapping("/rejectedrequests")
	public ArrayList<LoanDisbursal> getRejectedLoans(){
		
			return (ArrayList<LoanDisbursal>)service.getRejectedLoans();
		
	}
	
}
