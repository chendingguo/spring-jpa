package com.reyun.adi.account.dic;


public enum PackageEnum {

    PROBATION(1,"试用套餐"),
    FORMAL(2,"正式套餐");

    private Integer key;
    private String value;

    PackageEnum(Integer key, String value) {
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
