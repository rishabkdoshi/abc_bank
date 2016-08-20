package com.abcbank.dao;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.abcbank.domain.Customer;
import com.abcbank.domain.ResponseMessage;
import com.abcbank.domain.ResponseMessage.STATUS;;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
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

	public CustomerDAOImpl() {
		System.out.println("creating CustomerDAOImpl");
	}

	@Override
	public Customer getCustomer(long id) {
		return null;
	}

	@Override
	public ResponseMessage addCustomer(Customer customer) {

		String query = sqls.getProperty("customer.create");
		ResponseMessage responseMessage;

		try {
			template.update(query, customer.getFirstName(), customer.getLastName(), customer.getGender().ordinal(),
					customer.getDob(), customer.getEmail(), customer.getAddress(), customer.getCity(),
					customer.getState(), customer.getPin(), customer.getFax(), customer.getPhone());
		} catch (Exception e) {
			responseMessage = new ResponseMessage("Customer add failed, " + e.getMessage(), STATUS.FAILURE);
			return responseMessage;
		}
		responseMessage = new ResponseMessage("Customer add succeeded", STATUS.SUCCESS);
		return responseMessage;
	}

	@Override
	public long getCustomerCount() {
		return 0;
	}

}
