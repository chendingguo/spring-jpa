package com.reyun.adi.account.action;

import com.reyun.adi.account.dic.AdiErrorCodeEnum;
import com.reyun.adi.account.dic.PackageEnum;
import com.reyun.adi.account.dic.StatusEnum;
import com.reyun.adi.account.dic.TypeEnum;
import com.reyun.adi.account.service.AdiAuthorityService;
import com.reyun.framework.model.ResultModel;
import com.reyun.framework.model.ResultStatus;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(value = "/adi/auth")
@Api(value = "/api/adi/auth", description = "媒体管理", position = 12)
public class AdiAuthorityAction {


    @Autowired
    AdiAuthorityService adiAuthorityService;


    @ApiOperation(value = "获取产品分类", notes = "", httpMethod = "GET", response = ResultModel.class)
    @RequestMapping(value = "/listProductCategory", method = RequestMethod.GET)
    public ResultModel listProductCategory() {

        return ResultModel.OK(adiAuthorityService.listProductCategory(-1));
    }

    @ApiOperation(value = "媒体管理", notes = "", httpMethod = "GET", response = ResultModel.class)
    @RequestMapping(value = "/listMediaByCountry", method = RequestMethod.GET)
    public ResultModel listMediaByCountry(@RequestParam(required = false, defaultValue = "1") String countryId) {

        return ResultModel.OK(adiAuthorityService.listMediaByCountry(countryId));
    }


    @ApiOperation(value = "国家", notes = "", httpMethod = "GET", response = ResultModel.class)
    @RequestMapping(value = "/listCountryByContinent", method = RequestMethod.GET)
    public ResultModel listCountry(@RequestParam(required = false, defaultValue = "-1") int continentId) {

        return ResultModel.OK(adiAuthorityService.listCountryByContinent(continentId));
    }

    @ApiOperation(value = "获取州", notes = "", httpMethod = "GET", response = ResultModel.class)
    @RequestMapping(value = "/listContinent", method = RequestMethod.GET)
    public ResultModel listContinent() {

        return ResultModel.OK(adiAuthorityService.listContinent());
    }

    @ApiOperation(value = "获取州和国家", notes = "", httpMethod = "GET", response = ResultModel.class)
    @RequestMapping(value = "/listAllCountries", method = RequestMethod.GET)
    public ResultModel listAllCountries() {


        return ResultModel.OK(adiAuthorityService.listAllCountries());
    }

    @RequestMapping(value = "/modifyUserTrialCategory", method = RequestMethod.POST)
    @ApiOperation(value = "创建用户分类权限", notes = "", httpMethod = "POST", response = ResultModel.class)
    @RequiresPermissions("userpage")
    public ResultModel modifyUserTrialCategory(
            @RequestParam(defaultValue = "") long userId,
            @RequestParam(defaultValue = "") int zoneId,
            @RequestParam(defaultValue = "") String catIds) {
        return ResultModel.OK(adiAuthorityService.modifyUserTrialCategory(userId, zoneId, catIds));
    }

    @RequestMapping(value = "/modifyUserTrialMedia", method = RequestMethod.POST)
    @ApiOperation(value = "创建用户分类权限", notes = "", httpMethod = "POST", response = ResultModel.class)
    @RequiresPermissions("userpage")
    public ResultModel modifyUserTrialMedia(
            @RequestParam long userId,
            @RequestParam int zoneId,
            @RequestParam String mediaIds,
            @RequestParam(defaultValue = "", required = false) String countryIds) {
        return ResultModel.OK(adiAuthorityService.modifyUserTrialMedia(userId, zoneId, mediaIds, countryIds));
    }

    @RequestMapping(value = "/listUserCategory")
    @ApiOperation(value = "获取用户可见产品分类", notes = "", httpMethod = "POST", response = ResultModel.class)
    @RequiresPermissions("userpage")
    public ResultModel listUserCategory(
            @RequestParam(defaultValue = "") long userId,
            @RequestParam(defaultValue = "") int zoneId) {
        return ResultModel.OK(adiAuthorityService.listUserCategory(userId, zoneId));
    }

    @RequestMapping(value = "/listUserMedia")
    @ApiOperation(value = "获取用户可见媒体", notes = "", httpMethod = "POST", response = ResultModel.class)
    @RequiresPermissions("userpage")
    public ResultModel listUserMedia(
            @RequestParam(defaultValue = "") long userId,
            @RequestParam(defaultValue = "") int zoneId) {
        return ResultModel.OK(adiAuthorityService.listUserMedia(userId, zoneId));
    }

    @RequestMapping(value = "/modifyUserCountry", method = RequestMethod.POST)
    @ApiOperation(value = "创建用户国家", notes = "", httpMethod = "POST", response = ResultModel.class)
    @RequiresPermissions("userpage")
    public ResultModel modifyUserCountry(
            @RequestParam(defaultValue = "") long userId,
            @RequestParam(defaultValue = "") String countryIds) {
        return ResultModel.OK(adiAuthorityService.modifyUserCountry(userId, countryIds));
    }


    @RequestMapping(value = "/listUserCountry")
    @ApiOperation(value = "获取用户国家", notes = "", httpMethod = "GET", response = ResultModel.class)
    @RequiresPermissions("userpage")
    public ResultModel listUserCountry(
            long userId) {
        return ResultModel.OK(adiAuthorityService.listUserCountry(userId));
    }


    @RequestMapping(value = "/modifyUserCatAndMedia", method = RequestMethod.POST)
    @ApiOperation(value = "创建用户分类权限和媒体", notes = "", httpMethod = "POST", response = ResultModel.class)
    @RequiresPermissions("userpage")
    public ResultModel modifyUserCatAndMedia(
            @RequestParam long userId,
            @RequestParam int zoneId,
            @RequestParam(defaultValue = "") String catIds,
            @RequestParam(defaultValue = "") String mediaIds,
            @RequestParam(defaultValue = "", required = false) String countryIds) {

        int catResult = adiAuthorityService.modifyUserTrialCategory(userId, zoneId, catIds);
        int mediaResult = adiAuthorityService.modifyUserTrialMedia(userId, zoneId, mediaIds, countryIds);
        if (catResult > -1 && mediaResult > -1) {
            return ResultModel.OK(mediaResult+catResult);
        } else {
            return ResultModel.ERROR(ResultStatus.UPDATE_FAILED);
        }


    }


}
