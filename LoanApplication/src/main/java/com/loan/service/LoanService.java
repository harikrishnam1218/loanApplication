package com.loan.service;

import java.util.List;
import java.util.Optional;

import com.loan.exception.DBException;
import com.loan.exception.UserNotFoundException;
import com.loan.model.LoanData;
import com.loan.model.LoanInputData;

public interface LoanService {
	 LoanData applyLoan(LoanInputData inputData) throws UserNotFoundException;
	
	 LoanData getLoanData(Long lid) throws DBException;
	 
	 Optional<List<LoanData>> getAllLoanData();
	 
	 LoanData updateLoanData(LoanData loanData);
}
