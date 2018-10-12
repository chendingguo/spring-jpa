package com.reyun.adi.account.action;

import com.reyun.adi.account.model.User;
import com.reyun.adi.account.service.AdiUserService;
import com.reyun.framework.model.ResultModel;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/adi/user")
@Api(value = "/api/adi/user", description = "用户管理", position = 12)
public class AdiUserAction {
    @Autowired
    AdiUserService adiUserService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ApiOperation(value = "创建用户", httpMethod = "POST", response = ResultModel.class)
    @RequiresPermissions("userpage")
    public ResultModel create(
            @RequestBody User user) {
        return ResultModel.OK(adiUserService.createUser(user));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "更新用户", httpMethod = "POST", response = ResultModel.class)
    @RequiresPermissions("userpage")
    public ResultModel update(
            @RequestBody User user) {
        return ResultModel.OK(adiUserService.createUser(user));
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户", httpMethod = "POST", response = ResultModel.class)
    @RequiresPermissions("userpage")
    public ResultModel list(
            @RequestParam(required = false, defaultValue = "1") int pageIndex,
            @RequestParam(required = false, defaultValue = "50") int pageSize,
            @RequestParam(required = false, defaultValue = "") String keywrod) {
        return ResultModel.OK(adiUserService.listUsers(pageIndex,pageSize));
    }




}
