package com.example.demo.report;


import com.example.demo.model.TestScore;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class CreateScoreReport {

    public static final int COLUMN_INDEX_ID = 0;
    public static final int COLUMN_INDEX_NAME_STUDENT = 1;
    public static final int COLUMN_INDEX_FIRST_SCORE = 2;
    public static final int COLUMN_INDEX_SECOND_SCORE = 3;
    public static final int COLUMN_INDEX_FINAL_SCORE = 4;
    public static final int COLUMN_INDEX_SUMMARY_SCORE = 5;
    private static CellStyle cellStyleFormatNumber = null;

    public static void main(String[] args) throws IOException {
        final List<TestScore> testScores = getTestScores();
        final String excelFilePath = "D:\\ExcelReport\\testScores10001.xlsx";
        writeExcel(testScores, excelFilePath);
    }

    public static ByteArrayInputStream writeExcel(List<TestScore> testScores, String excelFilePath) throws IOException {
        // Create Workbook
        Workbook workbook = getWorkbook(excelFilePath);

        // Create sheet
        Sheet sheet = workbook.createSheet("testScores"); // Create sheet with sheet name

        int rowIndex = 0;

        // Write header
        writeHeader(sheet, rowIndex);

        // Write data
        rowIndex++;
        for (TestScore testScore : testScores) {
            // Create row
            Row row = sheet.createRow(rowIndex);
            // Write data on row
            writeTestScore(testScore, row);
            rowIndex++;
        }

        // Write footer
//        writeFooter(sheet, rowIndex);

        // Auto resize column witdth
        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);

        // Create file excel
        createOutputFile(workbook, excelFilePath);
        System.out.println("Done!!!");
        return null;
    }

    // Create dummy data
    private static List<TestScore> getTestScores() {
        List<TestScore> listTestScore = new ArrayList<>();
        TestScore testScore;
        for (int i = 1; i <= 5; i++) {
            testScore = new TestScore((long) i,  1, 2, 3, "Quy");
            listTestScore.add(testScore);
        }
        return listTestScore;
    }

    // Create workbook
    private static Workbook getWorkbook(String excelFilePath) throws IOException {
        Workbook workbook = null;

        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    // Write header with format
    private static void writeHeader(Sheet sheet, int rowIndex) {
        // create CellStyle
        CellStyle cellStyle = createStyleForHeader(sheet);

        // Create row
        Row row = sheet.createRow(rowIndex);

        // Create cells
        Cell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Id");

        cell = row.createCell(COLUMN_INDEX_NAME_STUDENT);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tên học sinh");

        cell = row.createCell(COLUMN_INDEX_FIRST_SCORE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Điểm 15 phút");

        cell = row.createCell(COLUMN_INDEX_SECOND_SCORE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Điểm 1 tiết");

        cell = row.createCell(COLUMN_INDEX_FINAL_SCORE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Điểm cuối kỳ");

        cell = row.createCell(COLUMN_INDEX_SUMMARY_SCORE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Điểm trung bình");
    }

    // Write data
    private static void writeTestScore(TestScore testScore, Row row) {
        if (cellStyleFormatNumber == null) {
            // Format number
            short format = (short)BuiltinFormats.getBuiltinFormat("#,##0");
            // DataFormat df = workTestScore.createDataFormat();
            // short format = df.getFormat("#,##0");

            //Create CellStyle
            Workbook workbook = row.getSheet().getWorkbook();
            cellStyleFormatNumber = workbook.createCellStyle();
            cellStyleFormatNumber.setDataFormat(format);
        }

        Cell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellValue(testScore.getId());

        cell = row.createCell(COLUMN_INDEX_NAME_STUDENT);
        cell.setCellValue(testScore.getName_student());

        cell = row.createCell(COLUMN_INDEX_FIRST_SCORE);
        cell.setCellValue(testScore.getFirstScore());
        cell.setCellStyle(cellStyleFormatNumber);

        cell = row.createCell(COLUMN_INDEX_SECOND_SCORE);
        cell.setCellValue(testScore.getSecondScore());
        cell.setCellStyle(cellStyleFormatNumber);

        cell = row.createCell(COLUMN_INDEX_FINAL_SCORE);
        cell.setCellValue(testScore.getFinalScore());
        cell.setCellStyle(cellStyleFormatNumber);


        // Create cell formula
        // totalMoney = price * quantity
        cell = row.createCell(COLUMN_INDEX_SUMMARY_SCORE, CellType.FORMULA);
        cell.setCellStyle(cellStyleFormatNumber);
        int currentRow = row.getRowNum() + 1;
        String columnFirstScore = CellReference.convertNumToColString(COLUMN_INDEX_FIRST_SCORE);
        String columnSecondScore = CellReference.convertNumToColString(COLUMN_INDEX_SECOND_SCORE);
        String columnFinalScore = CellReference.convertNumToColString(COLUMN_INDEX_FINAL_SCORE);

        cell.setCellFormula("("+columnFirstScore + currentRow + "+" + columnSecondScore + currentRow+ "*3+"
                + columnFinalScore + currentRow+ "*6)/10");
    }

    // Create CellStyle for header
    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }

    // Write footer
//    private static void writeFooter(Sheet sheet, int rowIndex) {
//        // Create row
//        Row row = sheet.createRow(rowIndex);
//        Cell cell = row.createCell(COLUMN_INDEX_SUMMARY_SCORE, CellType.FORMULA);
//        cell.setCellFormula("SUM(E2:E6)");
//    }

    // Auto resize column width
    private static void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }

    // Create output file
    private static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }
}
