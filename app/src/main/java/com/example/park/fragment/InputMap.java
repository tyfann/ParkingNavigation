package com.example.park.fragment;

public class InputMap {
    private int x;
    private int y;
    private int values;

    public InputMap() {
    }

    public InputMap(int x, int y, int values) {
        this.x = x;
        this.y = y;
        this.values = values;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getValues() {
        return values;
    }

    public void setValues(int values) {
        this.values = values;
    }
}
