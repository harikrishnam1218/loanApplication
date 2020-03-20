package com.loan.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="loan")
public class LoanData {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long lid;
	
	private Double loanamount;
	
	private Integer totaltenture;
	
	private Integer pendingtenture;

	private Integer intrest;
	
	private Double finalamount;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid")
	private User user;

	public Long getLid() {
		return lid;
	}

	public void setLid(Long lid) {
		this.lid = lid;
	}

	public Double getLoanamount() {
		return loanamount;
	}

	public void setLoanamount(Double loanamount) {
		this.loanamount = loanamount;
	}

	public Integer getTotaltenture() {
		return totaltenture;
	}

	public void setTotaltenture(Integer totaltenture) {
		this.totaltenture = totaltenture;
	}

	public Integer getPendingtenture() {
		return pendingtenture;
	}

	public void setPendingtenture(Integer pendingtenture) {
		this.pendingtenture = pendingtenture;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getIntrest() {
		return intrest;
	}

	public void setIntrest(Integer intrest) {
		this.intrest = intrest;
	}

	public Double getFinalamount() {
		return finalamount;
	}

	public void setFinalamount(Double finalamount) {
		this.finalamount = finalamount;
	}

	
	
}
