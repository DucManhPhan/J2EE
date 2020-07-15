package com.manhpd.table.order_table;

import com.aspose.words.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

public class CreatingOrderTable {

    public static void main(String[] args) {

    }

    public static void createOrderTable(List<String> headers, List<Order> orders, String pathFile) throws Exception {
        if (CollectionUtils.isEmpty(orders) || CollectionUtils.isEmpty(headers) || StringUtils.isEmpty(pathFile)) {
            System.out.println("Data or the file path is empty");
            return;
        }

        Document doc = new Document(pathFile);
        Table table = new Table(doc);
        doc.getFirstSection().getBody().appendChild(table);

        createHeaders(headers, doc, table);
        createContentRows(orders, doc, table, headers.size());
    }

    public static void createHeaders(List<String> headers, Document doc, Table table) {
        if (CollectionUtils.isEmpty(headers) || Objects.isNull(doc) || Objects.isNull(table)) {
            return;
        }

        CellInfo cellInfo = CellInfo.builder()
                                    .alignment(ParagraphAlignment.CENTER)
                                    .isBold(true)
                                    .backgroundColor(Color.GRAY)
                                    .rowHeight(20)
                                    .build();
        Row headerRow = createRow(doc, table, cellInfo);

        // create cell for each header
        for (String header : headers) {
            Cell cell = createCell(doc, header, cellInfo);
            headerRow.appendChild(cell);
        }

        table.appendChild(headerRow);
    }

    public static void createContentRows(List<Order> orders, Document doc, Table table, int numCells) throws IllegalAccessException {
        if (CollectionUtils.isEmpty(orders) || Objects.isNull(doc) || Objects.isNull(table)) {
            return;
        }

        CellInfo cellInfo = CellInfo.builder()
                .alignment(ParagraphAlignment.CENTER)
                .isBold(false)
                .backgroundColor(Color.WHITE)
                .rowHeight(20)
                .build();

        for (Order order : orders) {
            Row row = createRow(doc, table, cellInfo);
            Field[] fields = Order.class.getFields();

            for (Field field : fields) {
                Cell cell = createCell(doc, (String) field.get(order), cellInfo);
                row.appendChild(cell);
            }

            table.appendChild(row);
        }
    }

    public static Row createRow(Document doc, Table table, CellInfo cellInfo) {
        Row row = new Row(doc);
        RowFormat headerRowFormat = row.getRowFormat();
        headerRowFormat.setHeight(cellInfo.getRowHeight());
        headerRowFormat.setHeightRule(HeightRule.AT_LEAST);

        table.appendChild(row);
        return row;
    }

    public static Cell createCell(Document doc, String content, CellInfo cellInfo) {
        Cell cell = new Cell(doc);
        CellFormat cellFormat = cell.getCellFormat();
        cellFormat.setWidth(30.0);
        cellFormat.getShading().setBackgroundPatternColor(cellInfo.getBackgroundColor());

        // define the content of this cell
        Paragraph paragraph = new Paragraph(doc);
        ParagraphFormat paragraphFormat = paragraph.getParagraphFormat();
        paragraphFormat.setAlignment(cellInfo.getAlignment());

        Run run = new Run(doc);
        run.setText(content);
        run.getFont().setSize(12);
        run.getFont().setName("Times New Roman");
        run.getFont().setBold(cellInfo.isBold());

        paragraph.appendChild(run);
        cell.appendChild(paragraph);

        return cell;
    }

}
