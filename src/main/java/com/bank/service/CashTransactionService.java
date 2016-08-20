package com.bank.service;

import com.abcbank.domain.CashTransaction;
import com.abcbank.domain.ResponseMessage;

public interface CashTransactionService {
	
	public ResponseMessage deposit(CashTransaction transaction);

	public ResponseMessage withdraw(CashTransaction transaction);
}
