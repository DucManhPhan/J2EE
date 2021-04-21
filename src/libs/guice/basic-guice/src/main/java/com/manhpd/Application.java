package com.manhpd;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Application {
    public static void main( String[] args ) {
        usingProvideAnnotation();
    }

    public static void usingProvideAnnotation() {
        Injector injector = Guice.createInjector(new TextEditorModule());
        TextEditor_ProvideAnnotation textEditor = injector.getInstance(TextEditor_ProvideAnnotation.class);

        textEditor.makeSpellCheck();
    }

    public static void usingConstantBinding() {
        Injector injector = Guice.createInjector(new TextEditorModule());
        TextEditor_ConstantBinding textEditor = injector.getInstance(TextEditor_ConstantBinding.class);

        textEditor.makeConnection();
    }

    public static void usingCustomAnnotation() {
        Injector injector = Guice.createInjector(new TextEditorModule());
        TextEditor textEditor = injector.getInstance(TextEditor.class);

        textEditor.makeSpellChecker();
    }

    public static void usingNamedAnnotation() {
        Injector injector = Guice.createInjector(new TextEditorModule());
        TextEditor_NamedAnnotation textEditor = injector.getInstance(TextEditor_NamedAnnotation.class);

        textEditor.makeSpellCheck();
    }

}
