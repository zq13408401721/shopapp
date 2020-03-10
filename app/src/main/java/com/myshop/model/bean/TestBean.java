package com.myshop.model.bean;

import java.io.Serializable;

public class TestBean implements Serializable {

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int index;
    private String name;

}
