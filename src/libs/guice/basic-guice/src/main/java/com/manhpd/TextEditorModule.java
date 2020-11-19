package com.manhpd;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;
import com.manhpd.annotation.WinWord;

public class TextEditorModule extends AbstractModule {

    @Override
    protected void configure() {
//        bind(SpellChecker.class).to(SpellCheckerImpl.class);

        // binding with its subclass
//        bind(SpellCheckerImpl.class).to(WinWordSpellChecker.class);

        // using custom annotation to binding
//        bind(SpellChecker.class).annotatedWith(WinWord.class).to(WinWordSpellChecker.class);

        // using Named annotation to binding
//        bind(SpellChecker.class).annotatedWith(Names.named("OpenOffice")).to(OpenOfficeWordSpellCheckerImpl.class);

        // binding with value objects or constants
//        bind(String.class).annotatedWith(Names.named("JDBC")).toInstance("jdbc:mysql://localhost:5326/emp");

        // binding with Provider class
//        bind(SpellChecker.class).toProvider(SpellCheckProvider.class);

        // using constructor binding
        try {
            bind(SpellChecker.class).toConstructor(SpellChecker_ConstructorBinding.class.getConstructor(String.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        bind(String.class).annotatedWith(Names.named("JDBC"))
                          .toInstance("jdbc:mysql://localhost:5326/emp");
    }

//    @Provides
//    public SpellChecker spellChecker() {
//        String dbUrl = "jdbc:mysql://localhost:5326/emp";
//        String user = "user";
//        int timeout = 100;
//
//        SpellChecker SpellChecker = new SpellCheckerImpl_Params(dbUrl, user, timeout);
//        return SpellChecker;
//    }
}
