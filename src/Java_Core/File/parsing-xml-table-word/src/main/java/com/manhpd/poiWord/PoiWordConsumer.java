package com.manhpd.poiWord;

import com.manhpd.IInitializationStage;
import com.manhpd.dto.LstParameter;
import com.manhpd.utils.Constant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class PoiWordConsumer implements Runnable, IInitializationStage {

    private static final Logger logger = LogManager.getLogger(PoiWordConsumer.class);

    private BlockingQueue<LstParameter> data;

    private String wordFilePath;

    private XWPFDocument wordDocument;

    private XWPFTable table;

    public PoiWordConsumer(BlockingQueue<LstParameter> data, String wordFilePath) {
        this.data = data;
        this.wordFilePath = wordFilePath;
    }

    @Override
    public void run() {
        Objects.requireNonNull(this.data);
        this.preInitialize();

        int counter = 0;
        while (true) {
            try {
                LstParameter lstParameter = this.data.poll(Constant.TIMEOUT_DATA, TimeUnit.SECONDS);
                if (Objects.isNull(lstParameter)) {
                    break;
                }

                logger.info("Create a new row: " + lstParameter.getPARAM_NAME());
                XWPFTableRow row = this.table.createRow();
                row.getCell(Constant.COUNTER_TABLE_COL).setText(String.valueOf(++counter));
                row.getCell(Constant.PARAMETER_NAME_COL).setText(lstParameter.getPARAM_NAME());
                row.getCell(Constant.PARAMETER_VALUE_COL).setText(lstParameter.getPARAM_VALUE());
                row.getCell(Constant.PARAMETER_MEANING_COL).setText(lstParameter.getPARAM_MEANING());
            } catch (InterruptedException e) {
                logger.error(e);
                break;
            }
        }

        this.postInitialize();
    }

    @Override
    public void preInitialize() {
        logger.info("Initialize table");
        this.wordDocument = new XWPFDocument();
        this.table = this.wordDocument.createTable();
        this.createTableHeader();

        System.out.println("Size of lst parameter record: " + this.data.size());
    }

    @Override
    public void postInitialize() {
        FileOutputStream outputFile = null;
        try {
            outputFile = new FileOutputStream(new File(this.wordFilePath));
            this.wordDocument.write(outputFile);
        } catch (IOException e) {
            logger.error(e);
        } finally {
            try {
                outputFile.close();
                this.wordDocument.close();
            } catch (Exception ex) {
                logger.error(ex);
            }
        }

        logger.info("The end");
    }

    private void createTableHeader() {
        XWPFTableRow header = this.table.getRow(0);
        header.getCell(0).setText(Constant.COUNTER_TABLE_FIELD);
        header.addNewTableCell().setText(Constant.PARAMETER_NAME_FIELD);
        header.addNewTableCell().setText(Constant.PARAMETER_VALUE_FIELD);
        header.addNewTableCell().setText(Constant.PARAMETER_MEANING_FIELD);
    }
}
