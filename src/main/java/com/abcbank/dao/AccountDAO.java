package com.abcbank.dao;

import com.abcbank.domain.Account;
import com.abcbank.domain.ResponseMessage;

public interface AccountDAO {
	public Account getAccount(long id);

	public ResponseMessage addAccount(Account account);

	public long getAccountCount();

	public ResponseMessage getAccountBalance(long accountNo);
}
