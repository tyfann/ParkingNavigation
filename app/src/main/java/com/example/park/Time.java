package com.example.park;

import cn.bmob.v3.BmobObject;

public class Time extends BmobObject {
    String intime,outtime;


    public String getIntime() {
        return intime;
    }

    public void setIntime(String intime) {
        this.intime = intime;
    }

    public String getOuttime() {
        return outtime;
    }

    public void setOuttime(String outtime) {
        this.outtime = outtime;
    }
}
