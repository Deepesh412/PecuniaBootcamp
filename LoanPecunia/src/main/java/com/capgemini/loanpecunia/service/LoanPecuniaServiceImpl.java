package com.capgemini.loanpecunia.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.loanpecunia.dao.AccountDao;
import com.capgemini.loanpecunia.dao.LoanDisbursalDao;
import com.capgemini.loanpecunia.dao.LoanRequestDao;
import com.capgemini.loanpecunia.entities.Account;
import com.capgemini.loanpecunia.entities.LoanDisbursal;
import com.capgemini.loanpecunia.entities.LoanRequests;
import com.capgemini.loanpecunia.exceptions.BankAccountNotFound;

@Service
public class LoanPecuniaServiceImpl implements LoanPecuniaService {
    
	@Autowired
	private LoanRequestDao dao;
	@Autowired
	private AccountDao account;
	@Autowired
	private LoanDisbursalDao disburseDao;
	
	LoanDisbursal disburse = new LoanDisbursal();
	
	
	
	@Override
	public String loanRequest(LoanRequests loanreq) {
		
	      String s = loanreq.getAccountId();
	    
	      Optional<Account> details = account.findById(s);
	      
	      if(details.isPresent()) {
	    	  dao.save(loanreq);
	       
	    	  return "Your Loan Request is successful";
	      }
	      else
	      {
	    	  throw new BankAccountNotFound("No BankAccount found with " + loanreq.getAccountId() + "\n You need to have an Bank Account to apply Loan");
	      }
		
	}

	@Override
	public ArrayList<LoanRequests> getAllRequests() {
		
		return (ArrayList<LoanRequests>) dao.findAll();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<LoanDisbursal> getApproveLoans() {
		ArrayList<LoanRequests> approve = disburseDao.getApprovedLoans();
		Iterator iter = approve.iterator();
		while (iter.hasNext()) {
			LoanRequests l = (LoanRequests) iter.next();
			if(!(disburseDao.existsById(l.getLoanId()))) {
			disburse.setAccountId(l.getAccountId());
			disburse.setCreditScore(l.getCreditScore());
			disburse.setLoanAmount(l.getLoanAmount());
			disburse.setLoanId(l.getLoanId());
			disburse.setLoanRoi(l.getLoanRoi());
			disburse.setLoanStatus("Accepted");
			disburse.setLoanTenure(l.getLoanTenure());
			disburse.setLoanType(l.getLoanType());
			double interest = (l.getLoanAmount() * l.getLoanTenure() * l.getLoanRoi() / (100 * 12));
			double emi = ((l.getLoanAmount() + interest) / l.getLoanTenure());
			emi=Math.round(emi*100)/100;
			disburse.setEmi(emi);
			System.out.println(disburse);
			disburseDao.save(disburse);
		}else {
			continue;
		}
			}
		return disburseDao.findAllAccepted();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<LoanDisbursal> getRejectedLoans() {
		ArrayList<LoanRequests> approve = disburseDao.getRejectedLoans();
		Iterator iter = approve.iterator();
		while (iter.hasNext()) {
			LoanRequests l = (LoanRequests) iter.next();			
			disburse.setAccountId(l.getAccountId());
			disburse.setCreditScore(l.getCreditScore());
			disburse.setLoanAmount(0);
			disburse.setLoanId(l.getLoanId());
			disburse.setLoanRoi(l.getLoanRoi());
			disburse.setLoanStatus("Rejected");
			disburse.setLoanTenure(l.getLoanTenure());
			disburse.setLoanType(l.getLoanType());
			disburse.setEmi(0);
			System.out.println(disburse);
			disburseDao.save(disburse);			
		}
		return disburseDao.findAllRejected();
	}

	
	
	
}
