package com.reyun.adi.account.dic;


public enum TypeEnum {

    COMPANY(1,"企业用户"),
    OWNER(2,"个人用户");

    private Integer key;
    private String value;

    TypeEnum(Integer key, String value) {
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
