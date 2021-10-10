package com.manhpd.dto.inheritance;

import com.google.gson.annotations.SerializedName;

public class BaseClass {

    public int a;

    @SerializedName("type")
    public String typeName;

    public BaseClass() {
        this.typeName = "BaseClass"; // getClass().getName();
    }

    public BaseClass(int a) {
        this.a = a;
        this.typeName = "BaseClass"; // getClass().getName();
    }

    public int getInt() {
        return this.a;
    }

    public String toString() {
        return "Value of Base class is: " + this.a;
    }

}
