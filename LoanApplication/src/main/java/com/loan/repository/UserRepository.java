package com.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loan.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
