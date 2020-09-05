package com.capgemini.loanpecunia.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="loanrequests")

public class LoanRequests {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "loanId_Sequence")
	@SequenceGenerator(name = "loanId_Sequence", sequenceName = "loan_id_SEQ")
	private int loanId;
	
	@Column(length=12)
	private String accountId;
	
	@Column(length=8)
	private double loanAmount;
	
	@Column(length=8)
	private int loanTenure;
	
	@Column(length=8)
	private int creditScore;
	
	@Column(length=10)
	private double loanRoi;
	
	@Column(length=15)
	private String loanStatus;
	
	@Column(length=15)
	private String loanType;

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public int getLoanTenure() {
		return loanTenure;
	}

	public void setLoanTenure(int loanTenure) {
		this.loanTenure = loanTenure;
	}

	public int getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(int creditScore) {
		this.creditScore = creditScore;
	}

	public double getLoanRoi() {
		return loanRoi;
	}

	public void setLoanRoi(double loanRoi) {
		this.loanRoi = loanRoi;
	}

	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public LoanRequests(int loanId, String accountId, double loanAmount, int loanTenure, int creditScore,
			double loanRoi, String loanStatus, String loanType) {
		super();
		this.loanId = loanId;
		this.accountId = accountId;
		this.loanAmount = loanAmount;
		this.loanTenure = loanTenure;
		this.creditScore = creditScore;
		this.loanRoi = loanRoi;
		this.loanStatus = loanStatus;
		this.loanType = loanType;
	}

	public LoanRequests() {

	}
	
	@Override
	public String toString() {
		return "LoanRequests [loanId=" + loanId + ", accountId=" + accountId + ", loanAmount=" + loanAmount
				+ ", loanTenure=" + loanTenure + ", creditScore=" + creditScore + ", loanRoi=" + loanRoi
				+ ", loanStatus=" + loanStatus + ", loanType=" + loanType + "]";
	}
	
	
}
