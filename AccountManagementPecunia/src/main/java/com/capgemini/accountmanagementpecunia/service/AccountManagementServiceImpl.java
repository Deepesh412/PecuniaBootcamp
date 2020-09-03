package com.capgemini.accountmanagementpecunia.service;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.accountmanagementpecunia.dao.AccountManagementDao;
import com.capgemini.accountmanagementpecunia.entities.Customer;

@Service
@Transactional
public class AccountManagementServiceImpl implements AccountManagementService {

	@Autowired
	private AccountManagementDao dao;
	
	
	@Override
	public String addAccount(Customer customer) {
		
		dao.save(customer);
		return "account successfully created";
		
	}

	@Override
	public Customer findByAccountId(String accountId) {
		
		return dao.findByAccountId(accountId);
	}

	@Override
	public String updateName(String accountId, String customerName) {
	
		dao.updateName(accountId, customerName);
		return "Successfully updated";
	}

	@Override
	public String updateContact(String accountId, String contactNumber) {
	
		dao.updateContact(accountId, contactNumber);
		return "Successfully updated";
	}
	
	
	@Override
	public String updateAddress(String accountId, String address) {
	
		dao.updateAddress(accountId, address);
		return "Successfully updated";
	}
	
	@Override
	public String deleteAccount(String accountId) {
	
		dao.deleteAccount(accountId);
		dao.deleteCustomer(accountId);
		
		return "account deleted";
	}

}
