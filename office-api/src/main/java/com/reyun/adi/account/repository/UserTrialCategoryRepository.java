package com.reyun.adi.account.repository;


import com.reyun.adi.account.model.UserTrailCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserTrialCategoryRepository extends JpaRepository<UserTrailCategory, Long>, JpaSpecificationExecutor<UserTrailCategory> {



}
