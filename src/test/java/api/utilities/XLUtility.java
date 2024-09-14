package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {
	
	/**
	  This class contains the methods to read and write the data from and in xml file 
	  These methods will be used my methods defined in DataProvider class
	 */
	
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public Cell cell;
	public CellStyle cellStyle;
	String path;
	
	public XLUtility(String path) {
		this.path = path;
	}
	
	public int getRowCount(String sheetName) throws IOException {
		
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		workbook.close();
		fi.close();
		
		return rowCount;
	}
	
	public int getCellCount(String sheetName, int rowNum) throws IOException {
		
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		workbook.close();
		fi.close();
		
		return cellCount;
	}
	
	public String getCellData(String sheetName, int rowNum, int cellNum) throws IOException {
		
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(cellNum);
		
		
		DataFormatter formatter = new DataFormatter();
		String data;
		
		try {
			// it will get the data at the specified cell in string format even if the data is in other format
			data = formatter.formatCellValue(cell);
			
		} catch (Exception e) {
			
			data = "no data found at the specified cell";
		}
		
		workbook.close();
		fi.close();
		
		return data;
	}
	
	public void setCellData(String sheetName, int rowNum, int cellNum, String data) throws IOException {
		
		File excelFile = new File(path);
		
		// If excel file does not exist then we create one
		if(!excelFile.exists()) {
			workbook = new XSSFWorkbook();
			fo = new FileOutputStream(path);
			workbook.write(fo);
		}
		
		
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		
		//If excel does not have a sheet then we create one
		if(workbook.getSheetIndex(sheetName) == -1) {
			
			workbook.createSheet(sheetName);
		}
		
		// get the sheet
		sheet = workbook.getSheet(sheetName);
		
		//if sheet does not have a row then we create a new row
		if(sheet.getRow(rowNum) == null) {
			sheet.createRow(rowNum);
		}
		
		// get the row, create a cell and add the data to the cell
		row = sheet.getRow(rowNum);
		cell = row.createCell(cellNum);
		cell.setCellValue(data);
		
		// publish the data to the excel file
		fo = new FileOutputStream(path);
		workbook.write(fo);
		
		workbook.close();
		fi.close();
		fo.close();
	}
	
	public void fillCellGreenColor(String sheetName, int rowNum, int cellNum) throws IOException {
		
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		
		row = sheet.getRow(rowNum);
		cell = row.getCell(cellNum);
		
		cellStyle = workbook.createCellStyle();
		
		cellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(cellStyle);
		workbook.write(fo);
		
		workbook.close();
		fi.close();
		fo.close();
	}
	
	public void fillCellRedColor(String sheetName, int rowNum, int cellNum) throws IOException {
		
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		
		row = sheet.getRow(rowNum);
		cell = row.getCell(cellNum);
		
		cellStyle = workbook.createCellStyle();
		
		cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(cellStyle);
		workbook.write(fo);
		
		workbook.close();
		fi.close();
		fo.close();
	}
	
	

}
