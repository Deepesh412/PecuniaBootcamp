package com.capgemini.accountmanagementpecunia.service;

import com.capgemini.accountmanagementpecunia.entities.Account;
//import com.capgemini.accountmanagementpecunia.entities.Customer;

public interface AccountManagementService {

	String addAccount(Account account);

	Account findByAccountId(String accountId);

	String updateName(String accountId, String customerName);
	
	String updateContact(String accountId, String contactNumber);
	
	String updateAddress(String accountId, String address);

	String deleteAccount(String accountId);
	
}
