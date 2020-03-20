package com.loan.exception;

public class LoanRunTimeException extends RuntimeException{
	
	private String message;
	private Exception ex;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Exception getEx() {
		return ex;
	}
	public void setEx(Exception ex) {
		this.ex = ex;
	}
	public LoanRunTimeException(String message, Exception ex) {
		super();
		this.message = message;
		this.ex = ex;
	}
	public LoanRunTimeException() {
		super();
	}
	public LoanRunTimeException(String message) {
		super();
		this.message = message;
	}
	
}
