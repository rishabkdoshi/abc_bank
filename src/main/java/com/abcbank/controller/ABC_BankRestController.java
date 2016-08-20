package com.abcbank.controller;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.abcbank.domain.Account;
import com.abcbank.domain.CashTransaction;
import com.abcbank.domain.Customer;
import com.abcbank.domain.ResponseMessage;
import com.bank.service.AccountService;
import com.bank.service.CashTransactionService;
import com.bank.service.CustomerService;

@RestController
@RequestMapping("/service")
public class ABC_BankRestController {

	private CustomerService customerService;

	private AccountService accountService;

	private CashTransactionService cashTransactionService;


	@Inject
	public ABC_BankRestController(@Named("customerService") CustomerService customerService,
			@Named("accountService") AccountService accountService,
			@Named("cashTransactionService") CashTransactionService cashTransactionService) {
		this.customerService = customerService;
		this.accountService = accountService;
		this.cashTransactionService = cashTransactionService;
	}

	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseMessage addCustomer(@RequestBody Customer customer) {
		return customerService.addCustomer(customer);
	}

	@RequestMapping(value = "/addAccount", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseMessage addAccount(@RequestBody Account account) {
		return accountService.addAccount(account);
	}

	@RequestMapping(value = "/accountBalance", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseMessage getAccountBalance(@RequestBody long accountNo) {
		return accountService.getAccountBalance(accountNo);
	}

	@RequestMapping(value = "/deposit", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseMessage deposit(@RequestBody CashTransaction cashTransaction) {
		return cashTransactionService.deposit(cashTransaction);
	}

	@RequestMapping(value = "/withdraw", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseMessage withdraw(@RequestBody CashTransaction cashTransaction) {
		return cashTransactionService.withdraw(cashTransaction);
	}
}
