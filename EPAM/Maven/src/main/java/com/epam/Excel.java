package com.epam;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Excel {

    public static void main(String[] args) throws IOException, InvalidFormatException {
        //enumValues();
        enumMessages();

    }

    private static void enumValues() throws IOException, InvalidFormatException {
        Workbook workbook = WorkbookFactory.create(new File("/Users/atkachuk/Downloads/categories.xlsx"));
        Sheet mySheet = workbook.getSheetAt(0);
        for (int i = 1; i < mySheet.getPhysicalNumberOfRows(); i++) {
            Row row = mySheet.getRow(i);
            String stringCellValue = row.getCell(1).getStringCellValue();

            String enumValue = stringCellValue.toUpperCase();
            String name = stringCellValue;

            if (!enumValue.equals("OTHERS") && !enumValue.equals("OTHER")) {
                enumValue = enumValue.replace(" ", "_");
                enumValue = enumValue.replace("_&_", "_");
                System.out.println(enumValue + "(\"" + name + "\", " + "\"" + name + "\"),");
            }
        }
    }

    private static void enumMessages() throws IOException, InvalidFormatException {
        Workbook workbook = WorkbookFactory.create(new File("/Users/atkachuk/Downloads/categories.xlsx"));
        Sheet mySheet = workbook.getSheetAt(0);
        for (int i = 1; i < mySheet.getPhysicalNumberOfRows(); i++) {
            Row row = mySheet.getRow(i);
            String stringCellValue = row.getCell(1).getStringCellValue();

            String enumValue = stringCellValue.toUpperCase();
            String name = stringCellValue;

            if (!enumValue.equals("OTHERS") && !enumValue.equals("OTHER")) {
                enumValue = enumValue.replace(" ", "_");
                enumValue = enumValue.replace("_&_", "_");
                System.out.println("Category_" + enumValue + "_Name=" + name);
            }
        }
    }
}
