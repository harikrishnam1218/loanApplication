package com.loan.model;

import javax.validation.constraints.NotNull;

public class LoanInputData {

	@NotNull(message = "User Id Should not be null")
	private Long uid;
	
	@NotNull(message = "loan Amount Should not be null")
	private Double loanAmount;
	
	@NotNull(message = "no of years  Should not be null")
	private Integer years;

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Integer getYears() {
		return years;
	}

	public void setYears(Integer years) {
		this.years = years;
	}
	
	
}
