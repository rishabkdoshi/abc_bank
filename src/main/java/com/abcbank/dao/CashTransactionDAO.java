package com.abcbank.dao;

import com.abcbank.domain.CashTransaction;
import com.abcbank.domain.ResponseMessage;

public interface CashTransactionDAO {

	public ResponseMessage deposit(CashTransaction transaction);

	public ResponseMessage withdraw(CashTransaction transaction);

}
