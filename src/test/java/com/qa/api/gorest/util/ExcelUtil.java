package com.qa.api.gorest.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	public static Workbook book;
	public static Sheet sheet;

	public static String TESTDATA_SHEET_PATH = "C:\\Users\\User\\OneDrive\\Documents\\JavaTraining\\Nov2019RestAssuredApiFramework\\src\\main\\java\\com\\qa\\api\\gorest\\testdata\\GoRestTestData.xlsx";

	public static Object[][] getTestData(String sheetName) {

		try {
			FileInputStream ip = new FileInputStream(TESTDATA_SHEET_PATH);
			try {
				try {
					book = WorkbookFactory.create(ip);
				} catch (InvalidFormatException e) {
					e.printStackTrace();
				}
				sheet = book.getSheet(sheetName);
			} catch (EncryptedDocumentException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Object data[][] = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for(int i=0; i<sheet.getLastRowNum(); i++) {
			for(int k=0; k<sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString(); 
			}
		}
		return data;
	}

}
