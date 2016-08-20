package com.abcbank.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.abcbank.domain.Account;
import com.abcbank.domain.CashTransaction;
import com.abcbank.domain.ResponseMessage;
import com.abcbank.domain.ResponseMessage.STATUS;

@Repository
public class CashTransactionDAOImpl implements CashTransactionDAO {

	@Autowired
	JdbcTemplate template;

	@Autowired
	@Qualifier("sqls")
	private Properties sqls;

	AccountDAO accountDAO;

	@Autowired
	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	public Properties getSqls() {
		return sqls;
	}

	public void sqls(Properties sqls) {
		this.sqls = sqls;
	}

	public CashTransactionDAOImpl() {
		System.out.println("creating CashTransactionDAOImpl");
	}

	@Override
	public ResponseMessage deposit(CashTransaction cashTransaction) {

		Account account = accountDAO.getAccount(cashTransaction.getAccountNo());

		if (account == null) {
			return new ResponseMessage("Cash Deposit Failed, Account with given account number not found",
					STATUS.FAILURE);
		}

		String query = sqls.getProperty("account.deposit");
		ResponseMessage responseMessage;

		// account table updating
		try {
			template.update(query, cashTransaction.getAmount(), cashTransaction.getAccountNo());
		} catch (Exception e) {
			return new ResponseMessage("Deposit failed, Reason " + e.getMessage(), STATUS.FAILURE);
		}

		// making an entry in cashTransaction table
		query = sqls.getProperty("cashTransaction.create");

		String timestamp = "\"" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\"";

		try {
			template.update(query, cashTransaction.getAccountNo(), cashTransaction.getAmount(),
					cashTransaction.getType().ordinal(), timestamp, cashTransaction.getDescription());
		} catch (Exception e) {
			responseMessage = new ResponseMessage("Cash Deposit failed, " + e.getMessage(), STATUS.FAILURE);
			return responseMessage;
		}
		responseMessage = new ResponseMessage("Cash Deposit succeeded", STATUS.SUCCESS);
		return responseMessage;

	}

	@Override
	public ResponseMessage withdraw(CashTransaction cashTransaction) {
		Account account = accountDAO.getAccount(cashTransaction.getAccountNo());

		if (account == null) {
			return new ResponseMessage("Cash Withdrawal Failed, Account with given account number not found",
					STATUS.FAILURE);
		} else if (account.getBalance() < cashTransaction.getAmount()) {
			return new ResponseMessage("Cash Withdrawal Failed, Insufficient balance", STATUS.FAILURE);
		}

		String query = sqls.getProperty("account.withdraw");
		ResponseMessage responseMessage;

		// account table updating
		try {
			template.update(query, cashTransaction.getAccountNo());
		} catch (Exception e) {
			return new ResponseMessage("Deposit failed, Reason " + e.getMessage(), STATUS.FAILURE);
		}

		// making an entry in cashTransaction table
		query = sqls.getProperty("cashTransaction.create");

		String timestamp = "\"" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\"";

		try {
			template.update(query, cashTransaction.getAccountNo(), cashTransaction.getAmount(),
					cashTransaction.getType().ordinal(), timestamp, cashTransaction.getDescription());
		} catch (Exception e) {
			responseMessage = new ResponseMessage("Cash Deposit failed, " + e.getMessage(), STATUS.FAILURE);
			return responseMessage;
		}
		responseMessage = new ResponseMessage("Cash Deposit succeeded", STATUS.SUCCESS);
		return responseMessage;

	}

}
