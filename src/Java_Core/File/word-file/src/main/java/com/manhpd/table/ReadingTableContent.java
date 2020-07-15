package com.manhpd.table;

import com.aspose.words.*;
import com.manhpd.utils.Constant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ReadingTableContent {

    public static void main(String[] args) throws Exception {
        License license = new License();
        license.setLicense("Aspose.Words.lic");
        System.out.println("License set successfully.");

//        Document doc = new Document(Constant.DATA_DIR + "/com/[Kênh] Danh mục mã lỗi, thông báo.doc");
        Document doc = new Document(Constant.DATA_DIR + "/table-with-headers.docx");
        List<ErrorContent> errorContents = readTable(doc);

        errorContents.stream().forEach(error -> error.toString());
    }

    public static List<ErrorContent> readTable(Document doc) {
//        TableCollection tables = doc.getFirstSection().getBody().getTables();
//        if (Objects.isNull(tables)) {
//            System.out.println("Do not have the targeted table");
//            return Collections.emptyList();
//        }

//        Table targetedTable = tables.get(0);
        Table targetedTable = (Table) doc.getChild(NodeType.TABLE, 0, false);
        if (Objects.isNull(targetedTable)) {
            System.out.println("Do not exist any table in this document");
            return Collections.emptyList();
        }

        Row headerRow = targetedTable.getFirstRow();
        List<ErrorContent> errors = new ArrayList<>();

        for (Row row : targetedTable.getRows()) {
            if (row == headerRow) {
                continue;
            }

            CellCollection cells = row.getCells();

            // process to get errorCode
            Cell errorCodeCell = cells.get(3);
            Run errorCodeRun = (Run)errorCodeCell.getChildNodes(NodeType.RUN, false).get(0);
            String errorCode = errorCodeRun.getText();

            // process to get content of error
            Cell contentCell = cells.get(5);
            Run errorContentRun = (Run)contentCell.getChildNodes(NodeType.RUN, false).get(0);
            String errorContent = errorContentRun.getText();

            errors.add(new ErrorContent(errorCode, errorContent));
        }

        return errors;
    }

}
