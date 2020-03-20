package com.loan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loan.exception.DBException;
import com.loan.exception.UserNotFoundException;
import com.loan.model.LoanData;
import com.loan.model.LoanInputData;
import com.loan.service.LoanService;

@RestController
@RequestMapping("loan/api/")
public class LoanController {
	
	@Autowired
	private LoanService loanService;
	
	@PostMapping
	public ResponseEntity<LoanData> applyLoan(@RequestBody LoanInputData inputData) throws UserNotFoundException{
		LoanData loan =	loanService.applyLoan(inputData);
		return new ResponseEntity(loan,HttpStatus.OK);
	}
	@GetMapping("/{lid}")
	public ResponseEntity<LoanData> getLoanData(@PathVariable Long lid ) throws DBException{
		LoanData loan =	loanService.getLoanData(lid);
		return new ResponseEntity(loan,HttpStatus.OK);
	}
	
}
