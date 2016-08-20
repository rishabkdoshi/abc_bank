package com.abcbank.domain;

public class CashTransaction extends ObjectWithId {
	public static enum TRANSACTION_TYPE {
		CREDIT, DEBIT;
	};

	private long accountNo;

	private float amount;

	private String description;

	TRANSACTION_TYPE type;

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TRANSACTION_TYPE getType() {
		return type;
	}

	public void setType(String type) {
		this.type = TRANSACTION_TYPE.valueOf(type);
	}
}
