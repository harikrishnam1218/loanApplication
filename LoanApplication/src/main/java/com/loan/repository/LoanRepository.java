package com.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loan.model.LoanData;

@Repository
public interface LoanRepository extends JpaRepository<LoanData, Long> {

}
