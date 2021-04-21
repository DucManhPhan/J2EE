package com.manhpd;

import com.google.inject.Provider;

public class SpellCheckProvider implements Provider<SpellChecker> {

    @Override
    public SpellChecker get() {
        String dbUrl = "jdbc:mysql://localhost:5326/emp";
        String user = "user";
        int timeout = 100;

        SpellChecker SpellChecker = new SpellCheckerImpl_Params(dbUrl, user, timeout);
        return SpellChecker;
    }
}
