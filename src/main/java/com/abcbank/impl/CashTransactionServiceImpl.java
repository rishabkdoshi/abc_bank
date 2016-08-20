package com.abcbank.impl;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.abcbank.dao.CashTransactionDAO;
import com.abcbank.domain.CashTransaction;
import com.abcbank.domain.ResponseMessage;
import com.abcbank.domain.ResponseMessage.STATUS;
import com.bank.service.CashTransactionService;

@Named("transactionService")
public class CashTransactionServiceImpl implements CashTransactionService {

	CashTransactionDAO cashTransactionDAO;

	@Autowired // same as named or property ref
	public void setTransactionDAO(CashTransactionDAO cashTransactionDAO) {
		this.cashTransactionDAO = cashTransactionDAO;
	}

	@Transactional
	public ResponseMessage deposit(CashTransaction cashTransaction) {
		if (cashTransaction != null) {
			return cashTransactionDAO.deposit(cashTransaction);
		} else {
			return new ResponseMessage("Invalid details, transaction failed", STATUS.FAILURE);
		}

	}

	@Transactional
	public ResponseMessage withdraw(CashTransaction cashTransaction) {
		if (cashTransaction != null) {
			return cashTransactionDAO.withdraw(cashTransaction);
		} else {
			return new ResponseMessage("Invalid details, transaction failed", STATUS.FAILURE);
		}

	}
}
