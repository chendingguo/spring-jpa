package com.reyun.adi.account.repository;


import com.reyun.adi.account.model.UserUpdateLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserUpdateLogRepository extends JpaRepository<UserUpdateLog, Long>, JpaSpecificationExecutor<UserUpdateLog> {


}
