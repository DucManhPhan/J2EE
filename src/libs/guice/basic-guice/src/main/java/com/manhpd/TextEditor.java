package com.manhpd;

import com.google.inject.Inject;
import com.manhpd.annotation.WinWord;

public class TextEditor {

    private SpellChecker spellChecker;

    @Inject
    public TextEditor(@WinWord SpellChecker spellChecker) {
        this.spellChecker = spellChecker;
    }

    public void makeSpellChecker() {
        this.spellChecker.checkSpelling();
    }

}
