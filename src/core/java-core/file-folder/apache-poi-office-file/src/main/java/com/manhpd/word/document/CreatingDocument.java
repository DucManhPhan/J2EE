package com.manhpd.word.document;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class CreatingDocument {

    public static void main(String[] args) {
        XWPFDocument document = new XWPFDocument();
        try(OutputStream fileOut = new FileOutputStream("sample-document.docx")) {
            document.write(fileOut);
            System.out.println("Created file successfully");
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
