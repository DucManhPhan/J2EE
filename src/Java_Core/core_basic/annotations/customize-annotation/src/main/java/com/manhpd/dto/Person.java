package com.manhpd.dto;

import com.manhpd.annotations.validation.range.IntegerRangeValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private String name;

    private String address;

    @IntegerRangeValue(min = 1, max = 3)
    private int numOfChildren = 9;

    @IntegerRangeValue(min = 1, max = 3)
    public void setNumOfChildren(int num) {
        this.numOfChildren = num;
    }

    @Override
    public String toString() {
        return this.name + " is living in " + this.address + " with " + String.valueOf(this.numOfChildren) + " children.";
    }

}
