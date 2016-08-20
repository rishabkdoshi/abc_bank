package com.abcbank.domain;

public class Account {

	public static enum ACCOUNT_TYPE {
		SAVINGS, CURRENT;
	};

	ACCOUNT_TYPE type;

	float balance;

	String email;

	long customerId;

	public long getCustomerId() {
		return this.customerId;
	}

	public ACCOUNT_TYPE getType() {
		return type;
	}

	public void setType(String type) {
		this.type = ACCOUNT_TYPE.valueOf(type);
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(long initialDeposit) {
		this.balance = initialDeposit;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Account() {
	}
}
