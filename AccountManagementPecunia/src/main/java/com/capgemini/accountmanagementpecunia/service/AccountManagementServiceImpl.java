package com.capgemini.accountmanagementpecunia.service;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.accountmanagementpecunia.dao.AccountManagementDao;
import com.capgemini.accountmanagementpecunia.entities.Account;
import com.capgemini.accountmanagementpecunia.entities.Customer;

@Service
@Transactional
public class AccountManagementServiceImpl implements AccountManagementService {

	@Autowired
	private AccountManagementDao dao;

	@Override
	public String addAccount(Account account) {
		dao.save(account);
		return "Account created successfully";
	}

	@Override
	public Account findByAccountId(String accountId) {
		
		return dao.findByAccountId(accountId);
	}

	@Override
	public String updateName(String accountId, String customerName) {
		dao.updateName(accountId, customerName);
		return "Updated successfully";
	}

	@Override
	public String updateContact(String accountId, String contactNumber) {
		dao.updateContact(accountId, contactNumber);
		return "Updated successfully";
	}

	@Override
	public String updateAddress(String accountId, String address) {
		dao.updateAddress(accountId, address);
		return "Updated successfully";
	}
	
	

	@Override
	public String deleteAccount(String accountId) {
		dao.deleteAccount(accountId);
		return "deleted successfully";
	}

	
	
	
}
	