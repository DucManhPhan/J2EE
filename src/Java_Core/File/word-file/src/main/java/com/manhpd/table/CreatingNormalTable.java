package com.manhpd.table;

import com.aspose.words.*;
import com.manhpd.utils.Constant;

import java.awt.*;
import java.util.Objects;

public class CreatingNormalTable {

    public static void main(String[] args) throws Exception {
        Document doc = new Document();
//        createTable(doc);
        createTableWithoutBuilder(doc);

        System.out.println("Creating a normal table successfully");
    }

    public static void createTable(Document doc) throws Exception {
        if (Objects.isNull(doc)) {
            return;
        }

        DocumentBuilder builder = new DocumentBuilder(doc);
        builder.startTable();

        // define the first row
        builder.insertCell();
        builder.write("Row 1, Cell 1 Content");

        builder.insertCell();
        builder.endRow();

        // define the second row with two cells
        builder.insertCell();
        builder.write("Row 2, Cell 1 Content");

        builder.insertCell();
        builder.write("Row 2, Cell 2 Content");

        builder.endRow();

        // the end of a table
        builder.endTable();

        doc.save(Constant.DATA_DIR + "/table-tmp.docx");
    }

    public static void createTableWithoutBuilder(Document doc) throws Exception {
        if (Objects.isNull(doc)) {
            return;
        }

        // create a table and append it to the document
        Table table = new Table(doc);
        doc.getFirstSection().getBody().appendChild(table);

        // create the first row
        Row row = new Row(doc);
        RowFormat rowFormat = row.getRowFormat();
        rowFormat.setAllowBreakAcrossPages(true);
        rowFormat.setHeight(10);
        rowFormat.setHeadingFormat(true);

        table.appendChild(row);

        // setting for table when table has child nodes
//        table.autoFit(AutoFitBehavior.AUTO_FIT_TO_WINDOW);

        // create cell for the above row
        Cell cell = new Cell(doc);
        CellFormat cellFormat = cell.getCellFormat();
        cellFormat.getShading().setBackgroundPatternColor(Color.WHITE);
//        cellFormat.setWidth(20);

        cell.appendChild(new Paragraph(doc));
        cell.getFirstParagraph().appendChild(new Run(doc, "Row 1, Cell 1 Content"));

        cell.appendChild(new Paragraph(doc));
        cell.getLastParagraph().appendChild(new Run(doc, "Row 1, Cell 1-2 Content"));

        row.appendChild(cell);

        // create another cell based on the first row
        row.appendChild(cell.deepClone(false));
        row.getLastCell().appendChild(new Paragraph(doc));
        row.getLastCell().getFirstParagraph().appendChild(new Run(doc, "Row 1, Cell 2 Content"));

//        table.setAllowCellSpacing(false);
        doc.save(Constant.DATA_DIR + "/table-with-nodes.docx");
    }

}
