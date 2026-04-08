package com.ui.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ExcelReaderUtility {

	@Test
	public static Object[][] getExcelData(String fileName) throws IOException {

		File file = new File(System.getProperty("user.dir") + "\\testData\\LoginData.xlsx");
		FileInputStream fis = new FileInputStream(file);
		Workbook wb = new XSSFWorkbook(fis);
		Sheet sheet = wb.getSheet("LoginTestData");

		int totalRows = sheet.getLastRowNum();
		int totalCols = sheet.getRow(0).getLastCellNum();

		Object[][] value =new Object[totalRows][totalCols];

		for (int i = 0; i <totalRows; i++) {
			for (int j = 0; j < totalCols; j++) {
				Cell cell=sheet.getRow(i+1).getCell(j);
				if (CellType.NUMERIC == cell.getCellType()) {
					value[i][j] = String.valueOf(cell.getNumericCellValue());
				} else if (CellType.STRING == cell.getCellType()) {
					value[i][j]= cell.getStringCellValue();
				} else if (CellType.BOOLEAN == cell.getCellType()) {
					value[i][j]= String.valueOf(cell.getBooleanCellValue());
				}
			}
		}
		return value;
	}
}
