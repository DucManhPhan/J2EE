package com.manhpd.dto.inheritance;

public class DerivedClass1 extends BaseClass {

    public int b;

    public int getInt() {
        return this.b;
    }

    public DerivedClass1(int a, int b) {
        super(a);
        this.b = b;

        this.typeName = "DerivedClass1";
    }

    public String toString() {
        return "Value of Derived class 1 is: " + this.a + " - " + this.b;
    }

}
