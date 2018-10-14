package com.reyun.adi.account.repository;


import com.reyun.adi.account.model.UserTrailCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserTrialCategoryRepository extends JpaRepository<UserTrailCategory, Long>, JpaSpecificationExecutor<UserTrailCategory> {

    @Modifying
    @Transactional
    @Query(value="delete from user_trial_category  where user_id = :userId" , nativeQuery = true)
    int   deleteByUserId(@Param("userId") Long userId);

}
