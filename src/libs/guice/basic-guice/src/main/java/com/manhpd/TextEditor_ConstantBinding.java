package com.manhpd;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class TextEditor_ConstantBinding {

    private String dbUrl;

    @Inject
    public TextEditor_ConstantBinding(@Named("JDBC") String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public void makeConnection() {
        System.out.println(this.dbUrl);
    }

}
