package com.capgemini.accountmanagementpecunia.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.accountmanagementpecunia.entities.Customer;

public interface AccountManagementDao extends JpaRepository<Customer,Integer>{

		
		@Query("select f from Customer f where account_Id=?1")
		Customer findByAccountId(String accountId);
		
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
		
		
		@Modifying
		@Query("delete from Customer e where account_Id=?1")
		void deleteCustomer(String accountId);
	    
	}

