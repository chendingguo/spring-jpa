package com.reyun.adi.account.repository;


import com.reyun.adi.account.model.UserTrialCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserTrialCategoryRepository extends JpaRepository<UserTrialCategory, Long>, JpaSpecificationExecutor<UserTrialCategory> {

    @Modifying
    @Transactional
    @Query(value="delete from user_trial_category  where user_id = :userId" , nativeQuery = true)
    int   deleteByUserId(@Param("userId") Long userId);

}
