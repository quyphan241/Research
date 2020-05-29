package com.example.demo.report;

import com.example.demo.model.TestScore;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelScoreReport {
    public static ByteArrayInputStream scoresToExcel(List<TestScore> testScores) throws IOException {
        String[] COLUMNs = {"Id", "Tên học sinh", "Điểm 15 phút", "Điểm 1 tiết", "Điểm cuối kỳ","Điểm trung bình"};
        try(
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
        ){
            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("testScore");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Row for Header
            Row headerRow = sheet.createRow(0);

            // Header
            for (int col = 0; col < COLUMNs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(COLUMNs[col]);
                cell.setCellStyle(headerCellStyle);
            }

            // CellStyle for Age
//          CellStyle ageCellStyle = workbook.createCellStyle();
//          ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));

            int rowIdx = 1;
            for (TestScore testScore : testScores) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(testScore.getId());
                row.createCell(1).setCellValue(testScore.getFirstScore());
                row.createCell(2).setCellValue(testScore.getSecondScore());
                row.createCell(3).setCellValue(testScore.getFinalScore());

            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
