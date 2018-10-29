package com.bm.mytestdemo.enums;

/**
 * Created by xiao on 2018/9/20.
 */

public enum  MyEnum {
    NETWORK_NONE("未连接"), NETWORK_MOBILE("电话"), NETWORK_WIFI("wifi");

    private String name;
    MyEnum(String s) {
        name = s;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
