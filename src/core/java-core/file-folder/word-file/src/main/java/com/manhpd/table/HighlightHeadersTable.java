package com.manhpd.table;

import com.aspose.words.*;
import com.manhpd.utils.Constant;

import java.awt.*;
import java.util.Objects;

public class HighlightHeadersTable {

    public static void main(String[] args) throws Exception {
        Document doc = new Document();
        createHighlightHeadersTable(doc);

        System.out.println("Creating a document successfully");
    }

    /**
     * Creating a table with highlight headers that uses hierarchy structure
     *
     * @param doc
     * @throws Exception
     */
    public static void createHighlightHeadersTable(Document doc) throws Exception {
        if (Objects.isNull(doc)) {
            return;
        }

        // Table has 3 rows, 3 columns
        Table table = new Table(doc);
        doc.getFirstSection().getBody().appendChild(table);

        Row headerRow = new Row(doc);
        RowFormat headerRowFormat = headerRow.getRowFormat();
        headerRowFormat.setHeight(20);
        headerRowFormat.setHeightRule(HeightRule.AT_LEAST);

        Cell cell11 = createHeaderCell(doc, "Header Row, \n Cell 1");
        headerRow.appendChild(cell11);

        Cell cell12 = createHeaderCell(doc, "Header Row, \n Cell 2");
        headerRow.appendChild(cell12);

        Cell cell13 = createHeaderCell(doc, "Header Row, \n Cell 3");
        headerRow.appendChild(cell13);

        table.appendChild(headerRow);

        // create the second row
        Row secondRow = new Row(doc);

        Cell cell21 = createNormallCell(doc, "Row 1, Cell 1 Content");
        secondRow.appendChild(cell21);

        Cell cell22 = createNormallCell(doc, "Row 1, Cell 2 Content");
        secondRow.appendChild(cell22);

        Cell cell23 = createNormallCell(doc, "Row 1, Cell 3 Content");
        secondRow.appendChild(cell23);

        table.appendChild(secondRow);

        // create the third row
        Row thirdRow = new Row(doc);

        Cell cell31 = createNormallCell(doc, "Row 2, Cell 1 Content");
        thirdRow.appendChild(cell31);

        Cell cell32 = createNormallCell(doc, "Row 2, Cell 2 Content");
        thirdRow.appendChild(cell32);

        Cell cell33 = createNormallCell(doc, "Row 2, Cell 3 Content");
        thirdRow.appendChild(cell33);

        table.appendChild(thirdRow);

        doc.save(Constant.DATA_DIR + "/table-with-headers.docx");
    }

    public static Cell createHeaderCell(Document doc, String content) {
        // define the cell at (1, 1) position
        Cell cell = new Cell(doc);
        CellFormat cellFormat = cell.getCellFormat();
        cellFormat.setWidth(100.0);
        cellFormat.getShading().setBackgroundPatternColor(Color.BLUE);

        // define the content of this cell
        Paragraph paragraph = new Paragraph(doc);
        ParagraphFormat paragraphFormat = paragraph.getParagraphFormat();
        paragraphFormat.setAlignment(ParagraphAlignment.CENTER);

        Run run = new Run(doc);
        run.setText(content);
        run.getFont().setSize(12);
        run.getFont().setName("Times New Roman");
        run.getFont().setBold(true);

        paragraph.appendChild(run);
        cell.appendChild(paragraph);

        return cell;
    }

    public static Cell createNormallCell(Document doc, String content) {
        Cell cell = new Cell(doc);
        CellFormat cellFormat = cell.getCellFormat();
        cellFormat.getShading().setBackgroundPatternColor(Color.WHITE);

        Paragraph paragraph = new Paragraph(doc);
        paragraph.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
        paragraph.appendChild(new Run(doc, content));
        cell.appendChild(paragraph);

        return cell;
    }

    public static void createHeadersTableWithDocumentBuilder(Document doc) throws Exception {
        DocumentBuilder builder = new DocumentBuilder(doc);

        Table table = builder.startTable();

        // Make the header row.
        builder.insertCell();

        // Set the left indent for the table. Table wide formatting must be applied after
        // at least one row is present in the table.
//        table.setLeftIndent(20.0);

        // Set height and define the height rule for the header row.
        builder.getRowFormat().setHeight(40.0);
        builder.getRowFormat().setHeightRule(HeightRule.AT_LEAST);

        // Some special features for the header row.
        builder.getCellFormat().getShading().setBackgroundPatternColor(Color.GRAY);
        builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
        builder.getFont().setSize(16);
        builder.getFont().setName("Bauhaus 93");
        builder.getFont().setBold(true);

        builder.getCellFormat().setWidth(100.0);
        builder.write("Header Row,\n Cell 1");

        // We don't need to specify the width of this cell because it's inherited from the previous cell.
        builder.insertCell();
        builder.write("Header Row,\n Cell 2");

        builder.insertCell();
        builder.getCellFormat().setWidth(200.0);
        builder.write("Header Row,\n Cell 3");
        builder.endRow();

        // Set features for the other rows and cells.
        builder.getCellFormat().getShading().setBackgroundPatternColor(Color.BLUE);
        builder.getCellFormat().setWidth(100.0);
        builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);

        // Reset height and define a different height rule for table body
        builder.getRowFormat().setHeight(30.0);
        builder.getRowFormat().setHeight(HeightRule.AUTO);
        builder.insertCell();

        // Reset font formatting.
        builder.getFont().setSize(12);
        builder.getFont().setBold(false);

        // Build the other cells.
        builder.write("Row 1, Cell 1 Content");
        builder.insertCell();
        builder.write("Row 1, Cell 2 Content");

        builder.insertCell();
        builder.getCellFormat().setWidth(200.0);
        builder.write("Row 1, Cell 3 Content");
        builder.endRow();

        builder.insertCell();
        builder.getCellFormat().setWidth(100.0);
        builder.write("Row 2, Cell 1 Content");

        builder.insertCell();
        builder.write("Row 2, Cell 2 Content");

        builder.insertCell();
        builder.getCellFormat().setWidth(200.0);
        builder.write("Row 2, Cell 3 Content.");
        builder.endRow();
        builder.endTable();

        // Save the document to disk.
        doc.save(Constant.DATA_DIR + "DocumentBuilder_CreateFormattedTable_Out.doc");
    }

}
