package com.manhpd.word.table;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ReadingErrorsTable {

    public static void main(String[] args) {
        String path = "./[Kênh] Danh mục mã lỗi, thông báo.docx";
        List<ErrorVds> errors = readErrorsTable(path);

        errors.stream().forEach(errorVds -> errorVds.toString());
    }

    public static List<ErrorVds> readErrorsTable(String path) {
        if (StringUtils.isEmpty(path)) {
            System.out.println("The path is null");
            return Collections.emptyList();
        }

        List<ErrorVds> errorVds = new ArrayList<>();
        try (FileInputStream is = new FileInputStream(new File(path))) {
            XWPFDocument doc = new XWPFDocument(OPCPackage.open(is));
            XWPFTable table = doc.getTableArray(0);
            if (Objects.isNull(table)) {
                return Collections.emptyList();
            }

            List<XWPFTableRow> tableRows = table.getRows();
            int numRows = table.getNumberOfRows();

            for (int i = 1; i < numRows; ++i) {
                List<XWPFTableCell> tableCells = tableRows.get(i).getTableCells();
                if (CollectionUtils.isEmpty(tableCells)) {
                    continue;
                }

                XWPFTableCell errorCodeCell = tableCells.get(3);
                String errorCode = errorCodeCell.getText();

                XWPFTableCell errorContentCell = tableCells.get(5);
                String content = errorContentCell.getText();

                errorVds.add(new ErrorVds(errorCode, content));
            }
        } catch (IOException | InvalidFormatException ex) {
            System.out.println(ex);
        }

        return errorVds;
    }

}
