package com.loan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class LoanAccountHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException exception) {
		ErrorResponse er = new ErrorResponse();
		er.setCode("USER-400");
		er.setMessage(exception.getMessgae());
		return new ResponseEntity(er, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DBException.class)
	public ResponseEntity<ErrorResponse> handleDBException(DBException exception) {
		ErrorResponse er = new ErrorResponse();
		er.setCode("DB-500))");
		er.setMessage(exception.getMessgae());
		return new ResponseEntity(er, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(LoanRunTimeException.class)
	public ResponseEntity<ErrorResponse> handleRunTimeException(LoanRunTimeException exp) {
		ErrorResponse response = new ErrorResponse("LOAN-1000", exp.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

	}
	@ExceptionHandler(BatchJobException.class)
	public ResponseEntity<ErrorResponse> handleBatchJobException(BatchJobException ex){
		ErrorResponse response = new ErrorResponse("BATCH-100",ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}