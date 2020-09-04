package com.capgemini.accountmanagementpecunia.service;

//import com.capgemini.accountmanagementpecunia.entities.Account;
import com.capgemini.accountmanagementpecunia.entities.Customer;

public interface AccountManagementService {

	String addAccount(Customer customer);

	Customer findByAccountId(String accountId);

	String updateAccount(String accountId, String customerName, String contactNumber, String address);

	String deleteAccount(String accountId);
	
}
