package com.reyun.adi.account.dic;


/**
 * adi错误码
 */
public enum AdiErrorCodeEnum {
    //TODO:
    EMAIL_REPEATED_ERROR(-1,"邮箱名称已经存在");


    private Integer code;
    private String value;

    AdiErrorCodeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public static AdiErrorCodeEnum valueOf(int value) {
        for (AdiErrorCodeEnum s : values()) {
            if (s.code == value) {
                return s;
            }
        }
        throw new RuntimeException("Undefined AdiErrorCodeEnum " + value);
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

}
