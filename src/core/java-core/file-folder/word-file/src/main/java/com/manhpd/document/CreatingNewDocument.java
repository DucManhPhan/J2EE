package com.manhpd.document;

import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.manhpd.utils.Constant;

public class CreatingNewDocument {

    public static void main(String[] args) throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);

        builder.writeln("Hello, world!!!");

        builder.insertField(" MERGEFIELD CustomerName ");
        builder.insertParagraph();
        builder.insertField(" MERGEFIELD Address ");

        doc.save(Constant.DATA_DIR + "/Hello_word.docx");

        System.out.println("The end");
    }

}
