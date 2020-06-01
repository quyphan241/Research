package com.example.demo.report;

import com.example.demo.model.Class;
import com.example.demo.model.Subject;
import com.example.demo.model.TestScore;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelScoreReport {
    public static ByteArrayInputStream scoresToExcel(List<TestScore> testScores, Class _class, Subject subject) throws IOException {
        String[] COLUMNs = {"Số thứ tự","Tên học sinh ", "Điểm 15 phút", "Điểm 1 tiết", "Điểm cuối kỳ", "Điểm trung bình"};
        String[] classCOLS = {"Lớp","Học kỳ","Môn học"};

        try(
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
        ){
            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("TestScore");


            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.WHITE.getIndex());


            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);
            headerCellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerCellStyle.setBorderBottom(BorderStyle.THIN);

            // Row for Class
            Row classRow = sheet.createRow(0);
            // Class & Subject header
            for (int col = 0; col < classCOLS.length; col++) {
                Cell cell = classRow.createCell(col);
                cell.setCellValue(classCOLS[col]);
                cell.setCellStyle(headerCellStyle);
                sheet.autoSizeColumn(col);
            }

            // Row for Header
            Row headerRow = sheet.createRow(3);
            // Header
            for (int col = 0; col < COLUMNs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(COLUMNs[col]);
                cell.setCellStyle(headerCellStyle);
                sheet.autoSizeColumn(col);
            }


            // CellStyle for Age
//          CellStyle ageCellStyle = workbook.createCellStyle();
//          ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));

            int rowIdx = 4;
            for (TestScore testScore : testScores) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(rowIdx-4);
                row.createCell(1).setCellValue(testScore.getName_student());
//                row.createCell(2).setCellValue(testScore.getFirstScore());
//                row.createCell(3).setCellValue(testScore.getSecondScore());
//                row.createCell(4).setCellValue(testScore.getFinalScore());
//                double summaryScore = (testScore.getFirstScore()
//                        + testScore.getSecondScore()*3 + testScore.getFinalScore()*6)/10;
//                row.createCell(5).setCellValue(summaryScore);
                if(testScore.getFirstScore()==-1){
                    row.createCell(2).setCellValue("");
                    row.createCell(3).setCellValue("");
                    row.createCell(4).setCellValue("");
                }
                else {
                    row.createCell(2).setCellValue(testScore.getFirstScore());
                    row.createCell(3).setCellValue(testScore.getSecondScore());
                    row.createCell(4).setCellValue(testScore.getFinalScore());
                    double summaryScore = (testScore.getFirstScore()
                            + testScore.getSecondScore() * 3 + testScore.getFinalScore() * 6) / 10;
                    row.createCell(5).setCellValue(summaryScore);
                }
            }
            //Class& Subject
            Row classSubjectRow = sheet.createRow(1);
            classSubjectRow.createCell(0).setCellValue(_class.getName());
            classSubjectRow.createCell(1).setCellValue(_class.getName_semester());
            classSubjectRow.createCell(2).setCellValue(subject.getName());

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }

    }
}