package com.reyun.adi.account.repository;


import com.reyun.adi.account.model.UserTrialMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserTrialMediaRepository extends JpaRepository<UserTrialMedia, Long>, JpaSpecificationExecutor<UserTrialMedia> {

    @Modifying
    @Transactional
    @Query(value="delete from user_trial_media  where user_id = :userId", nativeQuery = true)

    int   deleteByUserId(@Param("userId") Long userId);

}
