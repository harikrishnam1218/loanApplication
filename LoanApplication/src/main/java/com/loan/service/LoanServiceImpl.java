package com.loan.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loan.exception.DBException;
import com.loan.exception.UserNotFoundException;
import com.loan.model.LoanData;
import com.loan.model.LoanInputData;
import com.loan.model.User;
import com.loan.repository.LoanRepository;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private UserService userService;
	
	public LoanData applyLoan(LoanInputData inputData) throws UserNotFoundException {
			Long uid=inputData.getUid();
			Optional<User> user=userService.findByUser(uid);
			LoanData loanEntity;
			
			if(user.isPresent() && Objects.nonNull(user.get())) {
				
				Double loanAmount=inputData.getLoanAmount();
				
				Integer years=inputData.getYears();
			
				int intrest=years>=2?(loanAmount<=500000?8:10):13;
				
				Double	intrestAmount=(intrest*years*loanAmount)/100;
				
				LoanData loan = loanDataMapper(user, loanAmount, years, intrest, intrestAmount);
				
				loanEntity=loanRepository.save(loan);
				
			}else {
				throw new UserNotFoundException("User Not abailable in DB");
			}
		return loanEntity;
	}

	private LoanData loanDataMapper(Optional<User> user, Double loanAmount, Integer years, int intrest,
		Double intrestAmount) {
		LoanData loan=new LoanData();
		loan.setUser(user.get());
		loan.setIntrest(intrest);
		loan.setLoanamount((-loanAmount));
		loan.setPendingtenture(years*12);
		loan.setTotaltenture(years*12);
		loan.setFinalamount(intrestAmount+loanAmount);
		return loan;
	}

	@Override
	public LoanData getLoanData(Long lid) throws DBException {
		
		Optional<LoanData> loan= loanRepository.findById(lid);
		if(!loan.isPresent()) {
			throw new DBException("Loan details not Found");
		}
		return loan.get();
		
	}

	@Override
	public Optional<List<LoanData>> getAllLoanData() {
		List<LoanData> allLoandata=loanRepository.findAll();
		return Optional.of(allLoandata);
	}

	@Override
	public LoanData updateLoanData(LoanData loanData) {
		return	loanRepository.save(loanData);
		//return null;
	}
	
	

}
