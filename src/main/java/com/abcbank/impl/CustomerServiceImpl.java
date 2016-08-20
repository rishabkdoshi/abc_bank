package com.abcbank.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.abcbank.dao.CustomerDAO;
import com.abcbank.domain.Customer;
import com.abcbank.domain.ResponseMessage;
import com.abcbank.domain.ResponseMessage.STATUS;
import com.bank.service.CustomerService;

@Named("customerService")
public class CustomerServiceImpl implements CustomerService {

	CustomerDAO customerDAO;

	@Autowired // same as named or property ref
	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	private static Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	private AtomicInteger atomicInteger = new AtomicInteger(0);

	public CustomerServiceImpl() {
		// init();
	}

	// In-memory list
	private List<Customer> customers = new ArrayList<>();

	public Customer getCustomer(long id) {
		logger.info("Retrieving id {}", id);
		for (Customer customer : customers) {
			if (customer.getId() == id) {
				return customer;
			}
		}
		return null;
	}

	@Transactional
	public ResponseMessage addCustomer(Customer customer) {
		if (customer != null) {
			return customerDAO.addCustomer(customer);
		} else {
			return new ResponseMessage("Customer details not found", STATUS.FAILURE);
		}
	}

	public long getCustomerCount() {
		return customers.size();
	}
}
