package com.abcbank.domain;

public class ResponseMessage {

	private String message;
	private STATUS status;

	public ResponseMessage(String message, STATUS status) {
		super();
		this.message = message;
		this.status = status;
	}

	public STATUS getStatus() {
		return status;
	}

	public void setStatus(STATUS status) {
		this.status = status;
	}

	public enum STATUS {
		SUCCESS, FAILURE;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
