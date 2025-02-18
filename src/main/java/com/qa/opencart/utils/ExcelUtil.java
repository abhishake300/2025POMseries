package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	private static Workbook book;
	private static Sheet sheet;
	private static final String TEST_DATA_PATH="unknown";
	
	public static Object[][] getTestData(String Sheetname) {
		
		System.out.println("Reading data from the sheet");
		Object data[][]=null;
		
		
		try {
			FileInputStream fp = new FileInputStream(TEST_DATA_PATH);
			book = new WorkbookFactory().create(fp);
			sheet= book.getSheet(Sheetname);
			data= new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			for(int i=0;i<sheet.getLastRowNum();i++) {
				for(int j=0;j<sheet.getRow(0).getLastCellNum();j++) {
					data[i][j] = sheet.getRow(i+1).getCell(j).toString();
				}
				
			}
			
			
		} catch (IOException | InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}

}
