package com.reyun.adi.account.repository;


import com.reyun.adi.account.model.UserTrialMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserTrialMediaRepository extends JpaRepository<UserTrialMedia, Long>, JpaSpecificationExecutor<UserTrialMedia> {



}
