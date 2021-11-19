package com.practice.littlebanker.exception;

public class BankTransactionException extends Exception {

	private String message;

	public BankTransactionException(String message) {
		super();
		this.message = message;
	}

}
