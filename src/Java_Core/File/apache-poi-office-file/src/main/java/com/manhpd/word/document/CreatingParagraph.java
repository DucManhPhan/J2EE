package com.manhpd.word.document;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CreatingParagraph {

    public static void main(String[] args) {
        XWPFDocument doc = new XWPFDocument();
        try (OutputStream os = new FileOutputStream("sample-document.docx")) {
            XWPFParagraph paragraph = doc.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText("Hello, world!!!");
            run.setBold(true);
            run.setFontFamily("Times New Roman");
            run.setFontSize(20);

            doc.write(os);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

}
