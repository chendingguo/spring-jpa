package com.reyun.framework.model;

import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Created by nolan on 15/11/2016.
 * description:
 */
public class ResultModel {

    /**
     * 返回码
     */
    @ApiModelProperty(value="状态码",required=true)
    private int code;

    /**
     * 返回结果描述
     */
    @ApiModelProperty(value="结果描述",required=true)
    private String message;

    /**
     * 返回内容
     */
    @ApiModelProperty(value="响应信息",required=true)
    private Object content;

    public ResultModel(int code, String message) {
        this.code = code;
        this.message = message;
        this.content = "";
    }

    public ResultModel(int code, String message, Object content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }

    public ResultModel(ResultStatus status) {
        this.code = status.getCode();
        this.message = status.getMessage();
        this.content = "";
    }

    public ResultModel(ResultStatus status, Object content) {
        this.code = status.getCode();
        this.message = status.getMessage();
        this.content = content;
    }

    public static ResultModel OK(Object content) {
        return new ResultModel(ResultStatus.SUCCESS, content);
    }

    public static ResultModel OK() {
        return new ResultModel(ResultStatus.SUCCESS);
    }

    public static ResultModel ERROR(ResultStatus error) {
        return new ResultModel(error);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getContent() {
        return content;
    }
}