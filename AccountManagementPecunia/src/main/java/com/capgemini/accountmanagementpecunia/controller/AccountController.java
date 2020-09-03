package com.capgemini.accountmanagementpecunia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.accountmanagementpecunia.entities.Customer;
import com.capgemini.accountmanagementpecunia.exceptions.IdAlreadyExistsException;
import com.capgemini.accountmanagementpecunia.exceptions.IdNotFoundException;
import com.capgemini.accountmanagementpecunia.service.AccountManagementService;

@RestController
@RequestMapping("/AccountManagement")
@CrossOrigin("http://localhost:4200")
public class AccountController {
	
	@Autowired
	private AccountManagementService service;
	
	@PostMapping("/create")
	public ResponseEntity<String> addAccount( @RequestBody Customer customer )throws IdAlreadyExistsException{
		Customer customer1 = service.findByAccountId(customer.getAccount().getAccountId());
		if(customer1!=null)
		{
			throw new IdAlreadyExistsException("Account ID already exists");
		}
		else
		{
			service.addAccount(customer);
			ResponseEntity<String> re = new ResponseEntity<String>("Account created successfully",HttpStatus.OK);
			return re;
		}
	}

	@GetMapping("/find/{accountId")
	public Customer findByAccountId(@PathVariable String accountId)
	{
		return service.findByAccountId(accountId);
	}
	
	
	@PutMapping("/update/{accountId}/{customerName}")
	public ResponseEntity<String> updateName(@PathVariable("accountId")String accountId,@PathVariable("customerName")String customerName) throws IdNotFoundException
	   {
		Customer customer=service.findByAccountId(accountId);
		if(customer==null) {
			throw new IdNotFoundException("Plese enter Valid account Id");
		}
		else {
			ResponseEntity<String> rs=  new ResponseEntity<String>(service.updateName(accountId, customerName),HttpStatus.OK);
			return rs;
		}	
	}
	
	@PutMapping("/updateContact/{accountId}/{contactNumber}")
	public ResponseEntity<String> updateContact(@PathVariable("accountId")String accountId,@PathVariable("contactNumber")String contactNumber) throws IdNotFoundException
	   {
		Customer customer=service.findByAccountId(accountId);
		if(customer==null) {
			throw new IdNotFoundException("Plese enter Valid account Id");
		}
		else {
			ResponseEntity<String> rs=  new ResponseEntity<String>(service.updateContact(accountId, contactNumber),HttpStatus.OK);
			return rs;
		}	
	}
	
	@PutMapping("/updateAddress/{accountId}/{address}")
	public ResponseEntity<String> updateAddress(@PathVariable("accountId")String accountId,@PathVariable("address")String address) throws IdNotFoundException
	   {
		Customer customer=service.findByAccountId(accountId);
		if(customer==null) {
			throw new IdNotFoundException("Plese enter Valid account Id");
		}
		else {
			ResponseEntity<String> rs=  new ResponseEntity<String>(service.updateAddress(accountId, address),HttpStatus.OK);
			return rs;
		}	
	}
	
	@DeleteMapping("/delete/{accountId}")
	public ResponseEntity<String> deleteAccount(@PathVariable("accountId") String accountId) throws IdNotFoundException 
	{
		Customer customer=service.findByAccountId(accountId);
		if(customer==null) {
			throw new IdNotFoundException("AccountId does not exists!");
		}
		else {
			ResponseEntity<String> rs =  new ResponseEntity<String>(service.deleteAccount(accountId),HttpStatus.OK);
			return rs;
		}
	}
	
}
