package com.manhpd;

import com.google.inject.Inject;

public class SpellCheckerImpl_Params implements SpellChecker {

    private String dbUrl;

    private String user;

    private Integer timeout;

    @Inject
    public SpellCheckerImpl_Params(String dbUrl,
                            String user,
                            Integer timeout) {
        this.dbUrl = dbUrl;
        this.user = user;
        this.timeout = timeout;
    }
    @Override
    public void checkSpelling() {
        System.out.println("Inside checkSpelling." );
        System.out.println(dbUrl);
        System.out.println(user);
        System.out.println(timeout);
    }
}
