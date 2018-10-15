package com.reyun.adi.account.dic;


import org.omg.CORBA.INTERNAL;

public enum PackageEnum {

    PROBATION(1,"试用"),
    FORMAL(2,"付费"),
    INTERNAL(3,"内部");

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
