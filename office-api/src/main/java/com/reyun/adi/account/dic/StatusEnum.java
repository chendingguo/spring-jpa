package com.reyun.adi.account.dic;


public enum StatusEnum {

    INIT(0,"未激活"),
    ACTIVE(1,"已开通"),
    PAST(2,"已过期"),
    FORBIDEN(3,"已停用");

    private Integer key;
    private String value;

    StatusEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}
