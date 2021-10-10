package com.manhpd.dto.inheritance.array;

import com.manhpd.dto.inheritance.BaseClass;

import java.util.List;

public class GsonArrayBaseclass {

    private List<BaseClass> baseClasses;

    public GsonArrayBaseclass(List<BaseClass> lst) {
        this.baseClasses = lst;
    }

}
