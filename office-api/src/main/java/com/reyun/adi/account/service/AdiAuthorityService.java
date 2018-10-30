package com.reyun.adi.account.service;

import com.reyun.adi.account.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AdiAuthorityService {
    /**
     * 根据国家获取媒体列表
     * @param countryIds
     * @return
     */

    List<Media> listMediaByCountry(String countryIds);

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
     * @param zoneId
     * @param catIds
     * @return
     */

     int createUserTrialCategory(long userId, int zoneId, String catIds);

    /**
     * 删除用户产品权限
     * @param userId
     * @return
     */

     int deleteUserTrilCategory(long userId, int zoneId);

    /**
     * 修改用户产品分类
     * @param userId
     * @param zoneId
     * @param catIds
     * @return
     */

     int modifyUserTrialCategory(long userId, int zoneId, String catIds);


    /**
     * 获取 用户可见产品分类列表
     * @param userId
     * @return
     */
    List<UserTrialCategory> listUserCategory(long userId, int zoneId);


    /**
     * 删除用户媒体权限
     * @param userId
     * @return
     */

    int deleteUserTrilMedia(long userId, int zoneId);
    int deleteUserCountry(long userId);
    /**
     * 创建用户媒体权限
     * @param userId
     * @param  zoneId
     * @param mediaIds
     * @return
     */
    int createUserTrialMedia(long userId, int zoneId, String mediaIds);


    /**
     * 修改用户媒体分类
     * @param userId
     * @param zoneId
     * @param mediaIds
     * @return
     */

    int modifyUserTrialMedia(long userId, int zoneId, String mediaIds, String countryIds);

    /**
     * 获取 用户可见媒体列表
     * @param userId
     * @returnMedia
     */
    List<UserTrialMedia> listUserMedia(long userId, int zoneId);


    /**
     * 获取用户国家
     * @param userId 用户ID
     * @return
     */

    List<UserCountry> listUserCountry(long userId);

    int  modifyUserCountry(long userId, String countryIds);


    User modifyUserZone(long userId, int zoneId);
}
