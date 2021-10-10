package com.manhpd;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class TextEditor_NamedAnnotation {

    private SpellChecker spellChecker;

    @Inject
    public TextEditor_NamedAnnotation(@Named("OpenOffice") SpellChecker spellChecker) {
        this.spellChecker = spellChecker;
    }

    public void makeSpellCheck() {
        this.spellChecker.checkSpelling();
    }

}
