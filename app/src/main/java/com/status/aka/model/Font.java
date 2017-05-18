package com.status.aka.model;

/**
 * Created by Aka on 4/20/2017.
 */

public class Font {
    String name;
    boolean check;

    public Font() {
    }

    public Font(String name, boolean check) {
        this.name = name;
        this.check = check;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
