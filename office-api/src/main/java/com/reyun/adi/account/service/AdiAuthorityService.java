package com.reyun.adi.account.service;

import com.reyun.adi.account.model.Continent;
import com.reyun.adi.account.model.Country;
import com.reyun.adi.account.model.Media;
import com.reyun.adi.account.model.ProductType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AdiAuthorityService {
    /**
     * 根据国家获取媒体列表
     * @param countryId
     * @return
     */

    List<Media> listMediaByCountry(int countryId);

    /**
     * 获取产品类别
     * @param typeId
     * @return
     */

    List<ProductType> listProductCategory(int typeId);

    /**
     *获取 国家
     * @param continentId
     * @return
     */

    List<Country> listCountryByContinent(int continentId);

    /**
     * 获取州
     * @return
     */
    List<Continent> listContinent();


    /**
     * 获取所有国家
     * @return
     */
    List<Map<String,Object>> listAllCountries();

    /**
     * 创建用户权限
     * @param userId
     * @param zoonId
     * @param catIds
     * @return
     */

     int createUserTrialCategory(long userId,int zoonId,String catIds);

    /**
     * 删除用户产品权限
     * @param userId
     * @return
     */

     int deleteUserTrilCategory(long userId);

    /**
     * 修改用户产品分类
     * @param userId
     * @param zoonId
     * @param catIds
     * @return
     */

     int modifyUserTrialCategory(long userId,int zoonId,String catIds);


    /**
     * 删除用户媒体权限
     * @param userId
     * @return
     */

    int deleteUserTrilMedia(long userId);
    /**
     * 创建用户媒体权限
     * @param userId
     * @param zoonId
     * @param mediaIds
     * @return
     */
    int createUserTrialMedia(long userId,int zoonId,String mediaIds);


    /**
     * 修改用户媒体分类
     * @param userId
     * @param zoonId
     * @param mediaIds
     * @return
     */

    int modifyUserTrialMedia(long userId,int zoonId,String mediaIds);




}
