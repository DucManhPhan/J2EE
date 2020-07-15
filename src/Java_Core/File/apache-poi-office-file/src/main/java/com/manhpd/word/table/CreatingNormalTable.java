package com.manhpd.word.table;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.io.*;
import java.util.List;
import java.util.Objects;

public class CreatingNormalTable {

    public static void main(String[] args) {
//        createNormalTable();
//        System.out.println("Created table successfully");

        String filePath = "sample-document.docx";
        readTableContent(filePath);
    }

    public static void createNormalTable() {
        XWPFDocument doc = new XWPFDocument();
        try (OutputStream os = new FileOutputStream(new File("sample-document.docx"))) {
            // Creating Table
            XWPFTable tab = doc.createTable();
            XWPFTableRow row = tab.getRow(0); // First row

            // Columns
            row.getCell(0).setText("Sl. No.");
            row.addNewTableCell().setText("Name");
            row.addNewTableCell().setText("Email");

            row = tab.createRow(); // Second Row
            row.getCell(0).setText("1.");
            row.getCell(1).setText("Irfan");
            row.getCell(2).setText("irfan@gmail.com");

            row = tab.createRow(); // Third Row
            row.getCell(0).setText("2.");
            row.getCell(1).setText("Mohan");
            row.getCell(2).setText("mohan@gmail.com");

            doc.write(os);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public static void readTableContent(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            System.out.println("The path of this file is null");
            return;
        }

        try (FileInputStream is = new FileInputStream(new File(filePath))) {
            XWPFDocument doc = new XWPFDocument(OPCPackage.open(is));
            XWPFTable table = doc.getTableArray(0);
            if (Objects.isNull(table)) {
                return;
            }

            List<XWPFTableRow> tableRows = table.getRows();
            int numRows = table.getNumberOfRows();

            for (int i = 1; i < numRows; ++i) {
                List<XWPFTableCell> tableCells = tableRows.get(i).getTableCells();
                if (CollectionUtils.isEmpty(tableCells)) {
                    continue;
                }

                System.out.println("At row " + i);
                for (XWPFTableCell cell : tableCells) {
                    System.out.print(cell.getText() + " ");
                }

                System.out.println();
            }

        } catch (IOException | InvalidFormatException ex) {
            System.out.println(ex);
        }
    }

}
