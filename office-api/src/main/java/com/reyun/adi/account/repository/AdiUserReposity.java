package com.reyun.adi.account.repository;

import com.reyun.adi.account.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Repository
public interface AdiUserReposity extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    @Query(value = "select * from user where company = ?1 and del_flag!=1 and sit_on=0 order by create_time desc limit 1", nativeQuery = true)
    User findCompany(String company);

    @Query(value = "select utm.media_id from user_trial_media utm left join media m on m.id=utm.media_id where utm.user_id = ?1 and utm.status=1 and m.status=1", nativeQuery = true)
    List<String> findUserMediaList(String id);

    @Query(value = "select id from media where status=1 order by id asc", nativeQuery = true)
    List<String> allMediasOnlyId();

    @Query(value = "select name from media where id = ?1 and status=1  limit 1", nativeQuery = true)
    String findMediaName(String valueOf);

    @Query(value = "select utm.media_id,utm.user_id,m.name medianame from user_trial_media utm left join media m on m.id = utm.media_id where utm.status = 1 and m.status = 1", nativeQuery = true)
    List<Object[]> getMediaWithNames();

    @Query(value = "select count(us.id),us.* from ( select * from user where del_flag != 1 and sit_on = 0  order by create_time desc ) us group by us.company having  count(us.id)>1", nativeQuery = true)
    List<User> getLastedUserByCompany();

    @Query(value = "select  type.name,c.id catid,c.name catname,c.type_id from product_category c left join product_type type on type.id = c.type_id order by type_id,catid", nativeQuery = true)
    List<Object[]> findUserTrailCategory();

    @Query(value = "select ucg.user_id,pcg.id,pcg.name from user_trial_category ucg left join  product_category pcg on pcg.id = ucg.cat_id  where ucg.status = 1", nativeQuery = true)
    List<Object[]> findUserCategryData();

    @Modifying
    @Transactional
    @Query(value = "UPDATE user set status = 1 where company = ?1 and sit_on=1", nativeQuery = true)
    void setSitOnInDate(String company);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user_trial_media set status=0 where user_id = ?1 ", nativeQuery = true)
    void closeUserMedia(Long id);

    @Query(value = "select name,id from media where id in (?1) and status=1 ", nativeQuery = true)
    List<Object[]> findMeidasByUsers(List<String> mediaList);

    @Modifying
    @Transactional
    @Query(value = "insert into user_trial_media (user_id,media_id,media_name,status,created_time) values (?1,?2,?3,1,now())", nativeQuery = true)
    void insertMedias(String userid, String medid, String name);

    @Modifying
    @Transactional
    @Query(value = "update user_trial_category set status = 0 , modify_time= now() where user_id = ?1", nativeQuery = true)
    void closeUserCompanyCat(Long id);

    @Query(value = "select id,name,type_id from product_category  where id in (?1) ", nativeQuery = true)
    List<Object[]> findCompanyCats(List<String> productCategorys);

    @Modifying
    @Transactional
    @Query(value = "insert into user_trial_category( user_id, cat_id,status, created_time,modify_time) values (?1,?2,?3,?4,null )", nativeQuery = true)
    void insetUserCategroys(Long id, String cat_id, String status, Date date);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user set status = ?2 where id = ?1", nativeQuery = true)
    void setUserStatus(Long id, int sta);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user set use_status = ?2 where id = ?1", nativeQuery = true)
    void setUserUseStatus(Long id, int i);

    @Query(value = "select utm.media_id,utm.user_id,m.name medianame from user_trial_media utm left join media m on m.id = utm.media_id where utm.status = 1 and m.status = 1 and utm.user_id = ?1", nativeQuery = true)
    List<Object[]> getMediaWithNamesByUser(Long id);

    @Query(value = "select  id,status,name from media where status =1", nativeQuery = true)
    List<Object[]> getMediaAllNames();
}


