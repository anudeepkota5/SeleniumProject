package com.mckinsey.lime.utils;

import com.mckinsey.lime.testDataBeans.FilePaths;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//TODO: Refactor
public class ExcelUtils {

    public static Workbook getWorkBook(String path) {
        Workbook workBook = null;
        try {
            workBook = WorkbookFactory.create(new File(path));
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
        return workBook;
    }

    public static Workbook getProductTogglerWorkBook() {
        return getWorkBook(FilePaths.TESTDATA_PRODUCT_TOGGLER);
    }

    public static Sheet getSheet(Workbook workBook, String sheetName) {
        for (Sheet sheet : workBook) {
            if (sheet.getSheetName().equalsIgnoreCase(sheetName))
                return sheet;
        }
        return null;
    }

    public static int getNumberOfRows(Sheet sheet) {
        //first 2 rows are for headings
        return sheet.getLastRowNum() - 1;
    }

    public static int getNumberOfColumns(Sheet sheet) {
        //first 2 rows are for headings
        return sheet.getRow(2).getLastCellNum();
    }

    public static List<List<String>> getSheetData(Sheet sheet, int headerRows) {
        DataFormatter dataFormatter = new DataFormatter();
        List<List<String>> data = new ArrayList<>();
        int maxRowSize = 0;
        for (Row row : sheet) {
            short currentRowSize = row.getLastCellNum();
            if (currentRowSize > maxRowSize)
                maxRowSize = currentRowSize;
        }


        for (int rn = headerRows; rn <= sheet.getLastRowNum(); rn++) {
            Row row = sheet.getRow(rn);
            //first 2(headerRows) rows are for headings
//            if (row.getRowNum() < headerRows)
//                continue;

            List<String> currentRow = new ArrayList<>();
            for (int cn = 0; cn < maxRowSize; cn++) {
                Cell cell = row.getCell(cn);
                if (cell == null) {
                    currentRow.add("");
                } else {
                    currentRow.add(dataFormatter.formatCellValue(cell));
                }
            }

            data.add(currentRow);
        }
        return data;
    }

    public static List<String> getRow(Sheet sheet, int rowNumberZeroBased) {
        DataFormatter dataFormatter = new DataFormatter();
        List<String> currentRow = new ArrayList<>();

        for (int rn = 0; rn <= sheet.getLastRowNum(); rn++) {
            Row row = sheet.getRow(rn);
            if (row.getRowNum() == rowNumberZeroBased) {
                for (int cn = 0; cn < row.getLastCellNum(); cn++) {
                    Cell cell = row.getCell(cn);
                    currentRow.add(dataFormatter.formatCellValue(cell));
                }
            }

        }
        return currentRow;
    }

    public static String getCellValue(List<String> currentRow, List<String> headerRow, String headerName) {
        return currentRow.get(headerRow.indexOf(headerName));
    }

    public static int getNoRowsInColumn(List<List<String>> sheetData, List<String> headerRow, String headerName) {
        int columnIndex = headerRow.indexOf(headerName);
        return sheetData.stream()
                .map(item -> item.get(columnIndex))
                .collect(Collectors.reducing(0, e -> 1, Integer::sum));

        //        return currentRow.get(headerRow.indexOf(headerName));
    }

    public static List<String> getColumn(List<List<String>> sheetData, List<String> headerRow, String headerName) {
        int columnIndex = headerRow.indexOf(headerName);
        return sheetData.stream()
                .map(item -> item.get(columnIndex))
                .filter(item -> !item.isEmpty())
                .collect(Collectors.toList());

        //        return currentRow.get(headerRow.indexOf(headerName));
    }

    @Test
    public void test() throws IOException, InvalidFormatException {
        Workbook workBook = WorkbookFactory.create(new File("src/main/resources/ProductToggler.xlsx"));
        Sheet sheet = workBook.getSheetAt(0);

        DataFormatter dataFormatter = new DataFormatter();

        sheet.forEach(row -> {
            row.forEach(cell -> {
                String cellAsString = dataFormatter.formatCellValue(cell);
                System.out.print(cellAsString + "\t");
            });
            System.out.println();
        });

    }
}
