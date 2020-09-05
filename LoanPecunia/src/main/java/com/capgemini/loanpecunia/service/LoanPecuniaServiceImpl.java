package com.capgemini.loanpecunia.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.loanpecunia.dao.AccountDao;
import com.capgemini.loanpecunia.dao.LoanDisbursalDao;
import com.capgemini.loanpecunia.dao.LoanRequestDao;
import com.capgemini.loanpecunia.dao.TransactionsDao;
import com.capgemini.loanpecunia.entities.Account;
import com.capgemini.loanpecunia.entities.LoanDisbursal;
import com.capgemini.loanpecunia.entities.LoanRequests;
import com.capgemini.loanpecunia.entities.Transactions;
import com.capgemini.loanpecunia.exceptions.BankAccountNotFound;

@Service
public class LoanPecuniaServiceImpl implements LoanPecuniaService {
    
	@Autowired
	private LoanRequestDao dao;
	@Autowired
	private AccountDao account;
	@Autowired
	private LoanDisbursalDao disburseDao;
	@Autowired
	private TransactionsDao transac;
	
	
	private Transactions transaction = new Transactions();
    private Random rand = new Random();
 	private long millis = System.currentTimeMillis();
	private Date date = new Date(millis);
	
	
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

	
	@Override
	public List<LoanDisbursal> getApproveLoans(String accountId) {
		LoanDisbursal disburse = new LoanDisbursal();
		List<LoanRequests> request = dao.selectById(accountId);
		@SuppressWarnings("rawtypes")
		Iterator itr = request.iterator();
		while(itr.hasNext())
		{
			LoanRequests requests = (LoanRequests) itr.next();
			if (requests.getCreditScore() >= 670 && (!(disburseDao.existsById(requests.getLoanId())))) {
				disburse.setAccountId(requests.getAccountId());
				disburse.setCreditScore(requests.getCreditScore());
				disburse.setLoanId(requests.getLoanId());
				disburse.setLoanRoi(requests.getLoanRoi());
				disburse.setLoanStatus("Accepted");
				disburse.setLoanTenure(requests.getLoanTenure());
				disburse.setLoanType(requests.getLoanType());
				double interest = ((requests.getLoanAmount() * requests.getLoanTenure() * requests.getLoanRoi())/(100*12));
				double emi = ((requests.getLoanAmount() + interest) / requests.getLoanTenure());
				disburse.setEmi(emi);
				disburse.setLoanAmount(requests.getLoanAmount() + interest);
				
				Account details = account.selectById(requests.getAccountId());
				details.setAmount(details.getAmount() + requests.getLoanAmount());
				account.save(details);
				
		        transaction.setAccountId(requests.getAccountId());
		        transaction.setTransAmount(requests.getLoanAmount());
				transaction.setTransDate(date);
				transaction.setTransFrom("BANK");
				transaction.setTransId(rand.nextInt(1000));
				transaction.setTransTo(requests.getAccountId());
				transaction.setTransType(requests.getLoanType());
				transac.save(transaction);
				
				disburseDao.save(disburse);
			}
		}
		return (List<LoanDisbursal>) disburseDao.findAllAccepted(accountId);
	}

	@Override
	public List<LoanDisbursal> getRejectedLoans(String accountId) {
	     LoanDisbursal disburse = new LoanDisbursal();
	     List<LoanRequests> request = dao.selectById(accountId);	
	     @SuppressWarnings("rawtypes")
	     Iterator itr = request.iterator();
	     while(itr.hasNext()) {
	    	 LoanRequests requests = (LoanRequests) itr.next();
	    	 
	    	 if(requests.getCreditScore() < 670 && (!(disburseDao.existsById(requests.getLoanId())))) {
	    		 disburse.setAccountId(requests.getAccountId());
	    		 disburse.setCreditScore(requests.getCreditScore());
	    		 disburse.setLoanAmount(0);
	    		 disburse.setLoanId(requests.getLoanId());
	    		 disburse.setLoanRoi(requests.getLoanRoi());
	    		 disburse.setLoanStatus("Rejected");
	    		 disburse.setLoanTenure(requests.getLoanTenure());
	    		 disburse.setLoanType(requests.getLoanType());
	    		 disburse.setEmi(0);
	    		 disburseDao.save(disburse);
	    	 }
	     }
	     return (List<LoanDisbursal>) disburseDao.findAllRejected(accountId);
	}

	/*@Override
	public String updateBalance(LoanDisbursal loandis) {
        
		if(loandis.getLoanTenure()>0)
		{
			loandis.setLoanId(loandis.getLoanId());
			loandis.setAccountId(loandis.getAccountId());
			loandis.setCreditScore(loandis.getCreditScore());
			loandis.setEmi(loandis.getEmi());
            
			double amount = loandis.getLoanAmount() - loandis.getEmi();
			
			loandis.setLoanAmount(amount);
			loandis.setLoanRoi(loandis.getLoanRoi());
			loandis.setLoanStatus(loandis.getLoanStatus());
			loandis.setLoanTenure(loandis.getLoanTenure() - 1);
			loandis.setLoanType(loandis.getLoanType());

			transaction.setAccountId(loandis.getAccountId());
			transaction.setTransAmount(loandis.getEmi());
			transaction.setTransDate(date);
			transaction.setTransFrom(loandis.getAccountId());
			transaction.setTransId(rand.nextInt(1000));
			transaction.setTransTo("Pecunia Bank");
			transaction.setTransType("EMI");
			transac.save(transaction);
			
			Account details = account.selectById(loandis.getAccountId());
			details.setAmount(details.getAmount() - loandis.getEmi());
			account.save(details);

			disburseDao.save(loandis);

			return loandis.getLoanType() + " EMI Rs/- " + loandis.getEmi() + " from " + loandis.getAccountId()
					+ " account is paid!! ";
		} else {
			return "No pending loan!!!";
		}
	}*/
			
		
}
