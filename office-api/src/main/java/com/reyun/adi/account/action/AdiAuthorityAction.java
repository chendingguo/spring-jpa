package com.reyun.adi.account.action;

import com.reyun.adi.account.model.UserTrailCategory;
import com.reyun.adi.account.service.AdiAuthorityService;
import com.reyun.adi.account.service.MediaService;
import com.reyun.framework.model.ResultModel;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/adi/auth")
@Api(value = "/api/adi/auth", description = "媒体管理", position = 12)
public class AdiAuthorityAction {
    @Autowired
    MediaService mediaService;

    @Autowired
    AdiAuthorityService adiAuthorityService;

    @ApiOperation(value = "媒体管理", notes = "", httpMethod = "GET", response = ResultModel.class)
    @RequestMapping(value = "/listMedia", method = RequestMethod.GET)
    public ResultModel listMedia(@RequestParam(required = false, defaultValue = "1") int pageIndex,
                                 @RequestParam(required = false, defaultValue = "10") int pageSize) {

        return ResultModel.OK(mediaService.listMedia(pageIndex, pageSize));
    }

    @ApiOperation(value = "媒体管理", notes = "", httpMethod = "GET", response = ResultModel.class)
    @RequestMapping(value = "/getMediaList", method = RequestMethod.GET)
    public ResultModel findMediaByStatus(@RequestParam(required = false, defaultValue = "1") int pageIndex,
                                         @RequestParam(required = false, defaultValue = "10") int pageSize,
                                         @RequestParam(required = false, defaultValue = "1") int status) {

        return ResultModel.OK(mediaService.findMediaByStatus(pageIndex, pageSize, status));
    }

    @ApiOperation(value = "获取产品分类", notes = "", httpMethod = "GET", response = ResultModel.class)
    @RequestMapping(value = "/listProductCategory", method = RequestMethod.GET)
    public ResultModel listProductCategory() {

        return ResultModel.OK(adiAuthorityService.listProductCategory(-1));
    }

    @ApiOperation(value = "媒体管理", notes = "", httpMethod = "GET", response = ResultModel.class)
    @RequestMapping(value = "/listMediaByCountry", method = RequestMethod.GET)
    public ResultModel listMediaByCountry(@RequestParam(required = false, defaultValue = "1") int countryId) {

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

    @RequestMapping(value = "/modifyUserTrialCategory", method = RequestMethod.POST)
    @ApiOperation(value = "创建用户分类权限", notes = "", httpMethod = "POST", response = ResultModel.class)
    @RequiresPermissions("userpage")
    public ResultModel modifyUserTrialCategory(
        @RequestParam(defaultValue = "") long userId,
        @RequestParam(defaultValue = "") int zoonId,
        @RequestParam(defaultValue = "") String catIds) {
        return ResultModel.OK(adiAuthorityService.createUserTrialCategory(userId, zoonId, catIds));
    }

    @RequestMapping(value = "/modifyUserTrialMedia", method = RequestMethod.POST)
    @ApiOperation(value = "创建用户分类权限", notes = "", httpMethod = "POST", response = ResultModel.class)
    @RequiresPermissions("userpage")
    public ResultModel modifyUserTrialMedia(
        @RequestParam(defaultValue = "") long userId,
        @RequestParam(defaultValue = "") int zoonId,
        @RequestParam(defaultValue = "") String mediaIds) {
        return ResultModel.OK(adiAuthorityService.createUserTrialMedia(userId, zoonId, mediaIds));
    }

    


}
