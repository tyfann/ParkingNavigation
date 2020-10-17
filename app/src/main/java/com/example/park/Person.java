package com.example.park;

import cn.bmob.v3.BmobObject;

public class Person extends BmobObject {
    private String name ;
    private String password;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
