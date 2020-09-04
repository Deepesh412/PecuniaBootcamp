package com.capgemini.accountmanagementpecunia.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.accountmanagementpecunia.entities.Account;
//import com.capgemini.accountmanagementpecunia.entities.Customer;
@Repository
public interface AccountManagementDao extends JpaRepository<Account,Serializable>{

	
	
		@Query("select f from Account f where account_Id=?1")
		Account findByAccountId(String accountId);
		
		@Modifying
		@Query("update Customer SET customer_Name=?2 where account_Id=?1")
		void updateName(String accountId, String customerName);
		
		@Modifying
		@Query("update Customer SET contact_Number=?2 where account_Id=?1")
		void updateContact(String accountId, String contactNumber);
		
		@Modifying
		@Query("update Customer SET address=?2 where account_Id=?1")
		void updateAddress(String accountId, String address);
		
		@Modifying
		@Query("delete from Account e where account_Id=?1")
		void deleteAccount(String accountId);
		
		
	//	@Modifying
	//	@Query("delete from Customer e where account_Id=?1")
	//	void deleteCustomer(String accountId);
	    
	}

