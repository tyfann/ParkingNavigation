package com.example.park.fragment;

import cn.bmob.v3.BmobObject;

public class Parking extends BmobObject {
    Integer Co_X,Co_Y,Values;
    Boolean EmptyIF;

    public void setCo_X(Integer co_X) {
        Co_X = co_X;
    }

    public Integer getCo_X() {
        return Co_X;
    }

    public void setCo_Y(Integer co_Y) {
        Co_Y = co_Y;
    }

    public Integer getCo_Y() {
        return Co_Y;
    }

    public Integer getValues() {
        return Values;
    }

    public void setValues(Integer values) {
        Values = values;
    }
}
