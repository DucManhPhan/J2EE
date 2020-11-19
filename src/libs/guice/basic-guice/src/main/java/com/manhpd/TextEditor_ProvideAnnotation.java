package com.manhpd;

import com.google.inject.Inject;

public class TextEditor_ProvideAnnotation {

    private SpellChecker spellChecker;

    @Inject
    public TextEditor_ProvideAnnotation(SpellChecker spellChecker) {
        this.spellChecker = spellChecker;
    }

    public void makeSpellCheck() {
        this.spellChecker.checkSpelling();
    }

}
