package com.bank.service;

import com.abcbank.domain.Account;
import com.abcbank.domain.ResponseMessage;

public interface AccountService {

	public Account getAccount(long id);

	public ResponseMessage addAccount(Account account);

	public long getAccountCount();

	public ResponseMessage getAccountBalance(long AccountNo);
}
