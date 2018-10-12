package com.reyun.adi.account.service;

import com.reyun.adi.account.model.Continent;
import com.reyun.adi.account.model.Country;
import com.reyun.adi.account.model.Media;
import com.reyun.adi.account.model.ProductType;
import org.springframework.stereotype.Repository;

import java.util.List;

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
     * 创建用户权限
     * @param userId
     * @param zoonId
     * @param catIds
     * @return
     */

     int createUserTrialCategory(long userId,int zoonId,String catIds);

    /**
     * 创建用户媒体权限
     * @param userId
     * @param zoonId
     * @param mediaIds
     * @return
     */
    int createUserTrialMedia(long userId,int zoonId,String mediaIds);


}
