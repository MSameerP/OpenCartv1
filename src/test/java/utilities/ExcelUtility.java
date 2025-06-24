package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet worksheet; 
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	public String path;
	
	public ExcelUtility(String path) {
		this.path = path;
	}
	
	public int getRowCount(String sheetName) throws Exception {
		fi= new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		worksheet = workbook.getSheet(sheetName);
		int rowCount = worksheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowCount;
	}
	
	public int getCellCount(String sheetName,int rowNum) throws Exception{
		fi= new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		worksheet = workbook.getSheet(sheetName);
		row = worksheet.getRow(rowNum);
		int cellcount = row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellcount;
		
	}
	
	public String getCellData(String sheetName,int rowNum,int columnNum) throws Exception {
		fi= new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		worksheet = workbook.getSheet(sheetName);
		row = worksheet.getRow(rowNum);
		cell = row.getCell(columnNum);
		
		String data;
		try {
			//data = cell.toString();
			DataFormatter formatter = new DataFormatter();
			data = formatter.formatCellValue(cell);
		}
		catch(Exception e) {
			data = "";
		}
		workbook.close();
		fi.close();
		return data;
	}
	
	public void setCellData(String sheetName,int rowNum,int columnNum,String data) throws Exception {
		
		File xlFIle = new File(path);
		if(!xlFIle.exists()) {                     //if file not exist then create a new file
			workbook = new XSSFWorkbook(xlFIle);
			fo = new FileOutputStream(xlFIle);
			workbook.write(fo);
			workbook.close();
			fo.close();
		}
		fi = new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		
		if(workbook.getSheetIndex(sheetName)==-1) {  //if sheet not exist then create new sheet
			workbook.createSheet(sheetName);
		}
		worksheet = workbook.getSheet(sheetName);
		
		if(worksheet.getRow(rowNum)==null) { //if row not exist then create new row
			row = worksheet.createRow(rowNum);
		}
		row = worksheet.getRow(rowNum);
		cell = row.createCell(columnNum);
		cell.setCellValue(data);
		fo = new FileOutputStream(path);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}
	
	public void fillGreenColor(String sheetName,int rowNum,int columnNum) throws Exception {
		fi= new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		worksheet = workbook.getSheet(sheetName);
		row = worksheet.getRow(rowNum);
		cell = row.getCell(columnNum);
		
		style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		fo = new FileOutputStream(path);
		workbook.write(fo);
		workbook.close();
		fo.close();
		fi.close();
	}
	
	public void fillRedColor(String sheetName,int rowNum,int columnNum) throws Exception {
		fi= new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		worksheet = workbook.getSheet(sheetName);
		row = worksheet.getRow(rowNum);
		cell = row.getCell(columnNum);
		style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		fo = new FileOutputStream(path);
		workbook.write(fo);
		workbook.close();
		fo.close();
		fi.close();
	}

}
