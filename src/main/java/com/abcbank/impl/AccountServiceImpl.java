package com.abcbank.impl;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.abcbank.dao.AccountDAO;
import com.abcbank.domain.Account;
import com.abcbank.domain.ResponseMessage;
import com.abcbank.domain.ResponseMessage.STATUS;
import com.bank.service.AccountService;

@Named("accountService")
public class AccountServiceImpl implements AccountService {

	AccountDAO accountDAO;

	@Autowired
	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	@Override
	public Account getAccount(long id) {
		return accountDAO.getAccount(id);
	}

	@Override
	@Transactional
	public ResponseMessage addAccount(Account account) {
		if (account != null) {
			return accountDAO.addAccount(account);
		} else {
			return new ResponseMessage("Account details not found", STATUS.FAILURE);
		}
	}

	@Override
	public long getAccountCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ResponseMessage getAccountBalance(long accountNo) {
		return accountDAO.getAccountBalance(accountNo);
	}

}
