package com.bank.service;

import com.abcbank.domain.Customer;
import com.abcbank.domain.ResponseMessage;

public interface CustomerService {

	public Customer getCustomer(long id);

	public ResponseMessage addCustomer(Customer customer);

	public long getCustomerCount();
}
