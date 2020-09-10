package com.capgemini.loanpecunia.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.loanpecunia.entities.LoanDisbursal;
import com.capgemini.loanpecunia.entities.LoanRequests;

@Repository
public interface LoanDisbursalDao extends JpaRepository<LoanDisbursal, Integer> 
{

	@Query("select r from LoanRequests r where creditScore>670")
	ArrayList<LoanRequests> getApprovedLoans();
	
	@Query("select r from LoanRequests r where creditScore<=670")
	ArrayList<LoanRequests> getRejectedLoans();
	
	@Query("select r from LoanDisbursal r where loanStatus='Accepted'")
	List<LoanDisbursal> findAllAccepted();
	
	@Query("select r from LoanDisbursal r where loanStatus='Rejected'")
	List<LoanDisbursal> findAllRejected();
	
	
	
}


