package com.reyun.adi.account.action;

import com.reyun.adi.account.dic.AdiErrorCodeEnum;
import com.reyun.adi.account.dic.PackageEnum;
import com.reyun.adi.account.dic.StatusEnum;
import com.reyun.adi.account.dic.TypeEnum;
import com.reyun.adi.account.model.User;
import com.reyun.adi.account.service.AdiUserService;
import com.reyun.framework.model.ResultModel;
import com.reyun.framework.model.ResultStatus;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/adi/user")
@Api(value = "/api/adi/user", description = "用户管理", position = 12)
public class AdiUserAction {
    @Autowired
    AdiUserService adiUserService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ApiOperation(value = "创建用户", httpMethod = "POST", response = ResultModel.class)
    @RequiresPermissions("userpage")
    public ResultModel create(@RequestBody User user) {

        long result = adiUserService.createUser(user);
        if (result > 0) {
            return ResultModel.OK(result);
        } else {
            int code = (int) result;
            String message = AdiErrorCodeEnum.valueOf(code).getValue();
            return new ResultModel(code, message);
        }


    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "更新用户", httpMethod = "POST", response = ResultModel.class)
    @RequiresPermissions("userpage")
    public ResultModel update(@RequestBody User user) {
        if (user.getId() == null) {
            return ResultModel.ERROR(ResultStatus.UPDATE_FAILED);
        }
        return ResultModel.OK(adiUserService.updateUser(user));
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户", httpMethod = "POST", response = ResultModel.class)
    @RequiresPermissions("userpage")
    public ResultModel list(
            @RequestParam(required = false, defaultValue = "1") int pageIndex,
            @RequestParam(required = false, defaultValue = "50") int pageSize,
            @RequestParam(required = false, defaultValue = "") String keyword,
            @RequestParam(required = false, defaultValue = "-1") int  status,
            @RequestParam(required = false, defaultValue = "-1") int  type,@RequestParam(required = false, defaultValue = "-1") int packageValue){
        Page<User> resut = adiUserService.listUsers(pageIndex, pageSize, keyword,status,type,packageValue);
        String[] columnTitle = {
                "账号邮箱",
                "公司名称",
                "账号类型",
                "账号分类",
                "账号状态",
                "起止日期",
                "创建日期",
                "联系人",
                "联系方式"
        };

        String[] columnName = {
                "email",
                "company",
                "whetherCompany",
                "onTrial",
                "status",
                "expriedTime",
                "createTime",
                "name",
                "phone"

        };
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("columnTitle", columnTitle);
        resultMap.put("columnName", columnName);
        resultMap.put("value", resut.getContent());
        resultMap.put("totalPages", resut.getTotalPages());
        resultMap.put("totalSize", resut.getTotalElements());


        return ResultModel.OK(resultMap);


    }

    @RequestMapping(value = "/findParam")
    @ApiOperation(value = "获取筛选条件", notes = "", response = ResultModel.class)
    @RequiresPermissions("userpage")
    public ResultModel findParam() {
        Map<String, Object> resultMap = new HashMap<>();

        List<Map<String, String>> packageList = new ArrayList();
        for (PackageEnum element : PackageEnum.values()) {
            Map<String, String> map = new HashMap<>();
            map.put("name", element.getValue());
            map.put("id", String.valueOf(element.getKey()));
            packageList.add(map);

        }

        List<Map<String, String>> typeList = new ArrayList();
        for (TypeEnum element : TypeEnum.values()) {
            Map<String, String> map = new HashMap<>();
            map.put("name", element.getValue());
            map.put("id", String.valueOf(element.getKey()));
            typeList.add(map);

        }

        List<Map<String, String>> statusList = new ArrayList();
        for (StatusEnum element : StatusEnum.values()) {
            Map<String, String> map = new HashMap<>();
            map.put("name", element.getValue());
            map.put("id", String.valueOf(element.getKey()));
            statusList.add(map);

        }
        resultMap.put("package", packageList);
        resultMap.put("type", typeList);
        resultMap.put("status", statusList);

        return ResultModel.OK(resultMap);

    }


}
