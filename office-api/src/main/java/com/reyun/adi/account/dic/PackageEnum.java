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

    public static PackageEnum valueOf(int value) {
        for (PackageEnum s : values()) {
            if (s.key == value) {
                return s;
            }
        }
        throw new RuntimeException("Undefined PackageEnum " + value);
    }

}
