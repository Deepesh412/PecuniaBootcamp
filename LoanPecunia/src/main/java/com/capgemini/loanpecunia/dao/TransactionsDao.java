package com.capgemini.loanpecunia.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.loanpecunia.entities.Transactions;

public interface TransactionsDao extends JpaRepository<Transactions, String> {

}


