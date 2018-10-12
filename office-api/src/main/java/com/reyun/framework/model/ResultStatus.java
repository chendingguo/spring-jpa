package com.reyun.framework.model;

import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Created by nolan on 15/11/2016.
 * description:
 */
public enum ResultStatus {
	SUCCESS(200, "成功"),
	CHANNEL_NOTEXIST(10000, "渠道不存在"),
	CHANNEL_UNIQUENAME_NOTEMPTY(10001, "渠道uniquename不允许为空"),
	NO_AUTH(250,"未授权"),
    UPDATE_FAILED(2000, "修改失败"),
    NOT_EXIST(2001, "不存在"),
	CPCALLBACK_INVALID(-1008,"回调名称不合法"),
    CAN_NOT_BE_NULL(-1007, "Both ds and type can not be null."),
	CPCALLBACK_REPEAT(-1009,"回调名称已经存在"),
    FILE_TYPE_ERROR(-6003, "文件格式错误"),
    APP_ERROR(-6004, "App不存在或者文件内容格式有误"),
    CONTENT_ERROR(-6005, "文件内容格式有误"),
    _REPEAT(-1008, "名称重复"),
    ERROR_WHITELIST(-1000, "该IP不在白名单列表中，无法发送短信."),
    APP_ID_PARAM_ERRO(-1010, "APP ID 参数错误"),
    TYPE_APP_NUM_LIMIT(-1011, "APP数量已到套餐限制,无法恢复"),
    APP_SDK_DEBUG_FAIL(-1012, "APP测试模式同步失败");

	/**
	 * 返回码
	 */
	@ApiModelProperty(value="状态码",required=true)
	private int code;

	/**
	 * 返回结果描述
	 */
	@ApiModelProperty(value="状态消息",required=true)
	private String message;

	ResultStatus(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}