package com.capgemini.loanpecunia.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.loanpecunia.entities.LoanDisbursal;

@Repository
public interface LoanDisbursalDao extends JpaRepository<LoanDisbursal, Integer> 
{
	@Query("select r from LoanDisbursal r where loanStatus='Accepted' and accountId=?1")
	List<LoanDisbursal> findAllAccepted(@Param("c") String s1);
	@Query("select r from LoanDisbursal r where loanStatus='Rejected' and accountId=?1")
	List<LoanDisbursal> findAllRejected(@Param("c") String s1);
}


