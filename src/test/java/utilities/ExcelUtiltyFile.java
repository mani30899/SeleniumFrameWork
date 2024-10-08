package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtiltyFile {

	String path;

	public ExcelUtiltyFile(String path) {
		this.path = path;
	}

	public FileInputStream excelFile;
	public Workbook workbook;
	public Sheet sheet;
	public Row row;

	// Set the Excel file and sheet
	public int getRowCount(String sheetName) throws IOException {
		excelFile = new FileInputStream(path);
		workbook = new XSSFWorkbook(excelFile);
		sheet = workbook.getSheet(sheetName);
		int rowcount = sheet.getLastRowNum();
		workbook.close();

		return rowcount;
	}

	// Get cell data
	public int getCellCount(String sheetName, int rowNum) throws IOException {
		excelFile = new FileInputStream(path);
		workbook = new XSSFWorkbook(excelFile);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		workbook.close();

		return cellCount;
	}

	public String getCellData(String sheetName, int rowNum, int colNum) throws Exception {
		excelFile = new FileInputStream(path);
		workbook = new XSSFWorkbook(excelFile);
		sheet = workbook.getSheet(sheetName);
		Cell cell = sheet.getRow(rowNum).getCell(colNum);

		DataFormatter formatter = new DataFormatter();
		workbook.close();
		excelFile.close();
		return formatter.formatCellValue(cell);
	}

	// Set cell data
	public void setCellData(String value, int rowNum, int colNum) throws IOException {
		Row row = sheet.getRow(rowNum);
		Cell cell = row.createCell(colNum);
		cell.setCellValue(value);

		try (FileOutputStream fileOut = new FileOutputStream("path_to_your_excel_file.xlsx")) {
			workbook.write(fileOut);
		}
	}
}
