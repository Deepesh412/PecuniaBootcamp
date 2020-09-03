package com.capgemini.accountmanagementpecunia.service;

import com.capgemini.accountmanagementpecunia.entities.Customer;

public interface AccountManagementService {

	String addAccount(Customer customer);

	Customer findByAccountId(String accountId);

	String updateName(String accountId, String customerName);
	
	String updateContact(String accountId, String ContactNumber);
	
	String updateAddress(String accountId, String address);

	String deleteAccount(String accountId);
	
}
