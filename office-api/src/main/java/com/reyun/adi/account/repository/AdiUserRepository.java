package com.reyun.adi.account.repository;


import com.reyun.adi.account.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdiUserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {


}
