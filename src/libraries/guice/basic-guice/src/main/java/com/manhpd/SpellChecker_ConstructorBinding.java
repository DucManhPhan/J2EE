package com.manhpd;

import com.google.inject.name.Named;

public class SpellChecker_ConstructorBinding implements SpellChecker {

    private String dbUrl;

    public SpellChecker_ConstructorBinding(){}

    public SpellChecker_ConstructorBinding(@Named("JDBC") String dbUrl) {
        this.dbUrl = dbUrl;
    }

    @Override
    public void checkSpelling() {
        System.out.println(this.dbUrl);
    }
}
