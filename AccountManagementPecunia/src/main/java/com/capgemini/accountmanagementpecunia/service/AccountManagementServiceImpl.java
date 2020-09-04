package com.capgemini.accountmanagementpecunia.service;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.accountmanagementpecunia.dao.AccountManagementDao;
//import com.capgemini.accountmanagementpecunia.entities.Account;
import com.capgemini.accountmanagementpecunia.entities.Customer;

@Service
@Transactional
public class AccountManagementServiceImpl implements AccountManagementService {

	@Autowired
	private AccountManagementDao dao;

	@Override
	public String addAccount(Customer customer) {
		dao.save(customer);
		return "Account created successfully";
	}

	@Override
	public Customer findByAccountId(String accountId) {
		
		return dao.findByAccountId(accountId);
	}

	@Override
	public String updateAccount(String accountId, String customerName, String contactNumber, String address) {
		dao.updateAccount(accountId, customerName, contactNumber, address);
		return "Updated successfully";
	}

	@Override
	public String deleteAccount(String accountId) {
		dao.deletecustomer(accountId);
		dao.deleteAccount(accountId);
		return "Deleted Account";
	}

	
	
	
}
	