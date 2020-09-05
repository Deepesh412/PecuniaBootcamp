package com.capgemini.loanpecunia.service;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.loanpecunia.entities.LoanDisbursal;
import com.capgemini.loanpecunia.entities.LoanRequests;

public interface LoanPecuniaService {

	public String loanRequest(LoanRequests loanreq);
	public ArrayList<LoanRequests> getAllRequests();
	public  List<LoanDisbursal> getApproveLoans(String accountId);
	public List<LoanDisbursal> getRejectedLoans(String accountId);
	//public String updateBalance(LoanDisbursal loandis);
}
