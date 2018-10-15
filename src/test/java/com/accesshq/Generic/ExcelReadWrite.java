package com.accesshq.Generic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReadWrite
{
	FileInputStream fis;
	XSSFWorkbook wb;
	
	
	public ExcelReadWrite(String filepath) throws IOException
	{
		fis = new FileInputStream(filepath);
		wb = new XSSFWorkbook(fis);
	}
	
	public int getRow(String sheetname)
	{
		XSSFSheet sheet = wb.getSheet(sheetname);
		return sheet.getLastRowNum();
	}
	
	public int getColumn(String sheetname)
	{
		XSSFSheet sheet = wb.getSheet(sheetname);
		return sheet.getRow(0).getLastCellNum();
	}
	
	public String readCell(String sheetname, int Row, int Col) 
	{
		XSSFSheet sheet = wb.getSheet(sheetname);
		XSSFCell cell = sheet.getRow(Row).getCell(Col);
		String CellText = "";
		
		if(cell.getCellType() == Cell.CELL_TYPE_STRING) 
		{
			CellText = cell.getStringCellValue();
		}
		if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC) 
		{
			CellText = String.valueOf(cell.getNumericCellValue());
		}
		
		if(cell.getCellType() == Cell.CELL_TYPE_BLANK) 
		{
			CellText = "";
		}
		
		return CellText;
		
	}

	public void writeCell(String sheetname, int Row, int Col, String value) 
	{
		XSSFSheet sheet = wb.getSheet(sheetname);
		sheet.getRow(Row).getCell(Col).setCellValue(value);
	}
	
	public void saveAndClose(String filepath) throws IOException 
	{
		fis.close();
		FileOutputStream fos = new FileOutputStream(filepath);
		wb.write(fos);
		fos.close();
	}
	
}

