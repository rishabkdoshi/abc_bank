package com.abcbank.dao;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.abcbank.domain.Account;
import com.abcbank.domain.Customer;
import com.abcbank.domain.ResponseMessage;
import com.abcbank.domain.ResponseMessage.STATUS;

@Repository
public class AccountDAOImpl implements AccountDAO {
	@Autowired
	JdbcTemplate template;

	@Autowired
	@Qualifier("sqls")
	private Properties sqls;

	public Properties getSqls() {
		return sqls;
	}

	public void setSqls(Properties sqls) {
		this.sqls = sqls;
	}

	public AccountDAOImpl() {
		System.out.println("creating AccountDAOImpl");
	}

	@Override
	public Account getAccount(long id) {
		return getAccountFromAccountNo(id);
	}

	@Override
	public ResponseMessage addAccount(Account account) {

		ResponseMessage responseMessage;

		int accountType = account.getType().ordinal();

		Customer customer = getCustomerFromEmail(account.getEmail());

		if (customer == null) {
			responseMessage = new ResponseMessage(
					"Account creation failed, email-id " + account.getEmail() + " not found", STATUS.FAILURE);
		}

		String query = sqls.getProperty("account.create");
		try {
			template.update(query, customer.getId(), accountType, account.getBalance());
		} catch (Exception e) {
			responseMessage = new ResponseMessage("Account creation failed, " + e.getMessage(), STATUS.FAILURE);
			return responseMessage;
		}

		responseMessage = new ResponseMessage("Account creation succeeded", STATUS.SUCCESS);
		return responseMessage;
	}

	private Customer getCustomerFromEmail(String email) {
		String query = sqls.getProperty("customer.retrieve");
		Customer customer;
		try {
			customer = template.queryForObject(query, new Object[] { email },
					new BeanPropertyRowMapper(Customer.class));
		} catch (Exception e) {
			return null;
		}
		return customer;
	}

	@Override
	public long getAccountCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ResponseMessage getAccountBalance(long accountNo) {
		Account account = getAccountFromAccountNo(accountNo);

		if (account == null) {
			return new ResponseMessage("Couldnt find account with accountNo " + accountNo, STATUS.FAILURE);
		}

		return new ResponseMessage("The account balance for accountNo " + accountNo + " is " + account.getBalance(),
				STATUS.SUCCESS);

	}

	/**
	 * to be called by CashTransactionDAOImpl
	 * 
	 * @param accountNo
	 *            the accountNo
	 * @return accountBalance accountBalance or -1 if account not found
	 */
	protected float getCurrentBalance(int accountNo) {
		Account account = getAccountFromAccountNo(accountNo);
		if (account == null) {
			return -1;
		} else {
			return account.getBalance();
		}
	}

	private Account getAccountFromAccountNo(long accountNo) {
		String query = sqls.getProperty("account.balance");
		Account account;
		try {
			account = template.queryForObject(query, new Object[] { accountNo },
					new BeanPropertyRowMapper(Account.class));
		} catch (Exception e) {
			return null;
		}
		return account;

	}

}
