package com.manhpd.dto.inheritance;

public class DerivedClass2 extends BaseClass {

    public int c;

    public DerivedClass2(int a, int c) {
        super(a);
        this.c = c;

        this.typeName = "DerivedClass2";
    }

    public int getInt() {
        return this.c;
    }

    public String toString() {
        return "Value of Derived class 2 is: " + this.a + " - " + this.c;
    }

}
