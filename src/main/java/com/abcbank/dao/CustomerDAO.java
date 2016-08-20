package com.abcbank.dao;

import com.abcbank.domain.Customer;
import com.abcbank.domain.ResponseMessage;

public interface CustomerDAO {
	public Customer getCustomer(long id);

	public ResponseMessage addCustomer(Customer customer);

	public long getCustomerCount();
}
