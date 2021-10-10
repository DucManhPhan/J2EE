package com.manhpd.document;

import com.aspose.words.Document;

import java.util.Objects;

public class ReadingDocument {

    public static void main(String[] args) {

    }

    public static int getPageNumber(Document doc) throws Exception {
        if (Objects.isNull(doc)) {
            throw new IllegalArgumentException("Document of Aspose.Words is invalid");
        }

        return doc.getPageCount();
    }

}
