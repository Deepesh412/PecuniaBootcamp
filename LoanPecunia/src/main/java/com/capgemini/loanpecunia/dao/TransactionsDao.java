package com.capgemini.loanpecunia.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.loanpecunia.entities.Transactions;

@Repository
public interface TransactionsDao extends JpaRepository<Transactions, String> {

}


