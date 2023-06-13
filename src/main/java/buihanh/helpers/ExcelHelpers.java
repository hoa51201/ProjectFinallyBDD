
package buihanh.helpers;

import buihanh.utils.Log;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelHelpers {

	private FileInputStream fis;
	private static FileOutputStream fileOut;
	private static Workbook workbook;
	private static Sheet sheet;
	private static Cell cell;
	private static Row row;
	private static String excelFilePath;
	private static Map<String, Integer> columns = new HashMap<>();

	public List<Map<String, String>> getData(String excelFilePath, String sheetName)
			throws InvalidFormatException, IOException {
		Sheet sheet = getSheetByName(excelFilePath, sheetName);
		return readSheet(sheet);
	}

	public List<Map<String, String>> getData(String excelFilePath, int sheetNumber)
			throws InvalidFormatException, IOException {
		Sheet sheet = getSheetByIndex(excelFilePath, sheetNumber);
		return readSheet(sheet);
	}

	private Sheet getSheetByName(String excelFilePath, String sheetName) throws IOException, InvalidFormatException {
		Sheet sheet = getWorkBook(excelFilePath).getSheet(sheetName);
		return sheet;
	}

	private Sheet getSheetByIndex(String excelFilePath, int sheetNumber) throws IOException, InvalidFormatException {
		Sheet sheet = getWorkBook(excelFilePath).getSheetAt(sheetNumber);
		return sheet;
	}

	private Workbook getWorkBook(String excelFilePath) throws IOException, InvalidFormatException {
		return WorkbookFactory.create(new File(excelFilePath));
	}

	private List<Map<String, String>> readSheet(Sheet sheet) {
		Row row;
		int totalRow = sheet.getPhysicalNumberOfRows();
		List<Map<String, String>> excelRows = new ArrayList<Map<String, String>>();
		int headerRowNumber = getHeaderRowNumber(sheet);
		if (headerRowNumber != -1) {
			int totalColumn = sheet.getRow(headerRowNumber).getLastCellNum();
			int setCurrentRow = 1;
			for (int currentRow = setCurrentRow; currentRow <= totalRow; currentRow++) {
				row = getRow(sheet, sheet.getFirstRowNum() + currentRow);
				LinkedHashMap<String, String> columnMapdata = new LinkedHashMap<String, String>();
				for (int currentColumn = 0; currentColumn < totalColumn; currentColumn++) {
					columnMapdata.putAll(getCellValue(sheet, row, currentColumn));
				}
				excelRows.add(columnMapdata);
			}
		}
		return excelRows;
	}

	private int getHeaderRowNumber(Sheet sheet) {
		Row row;
		int totalRow = sheet.getLastRowNum();
		for (int currentRow = 0; currentRow <= totalRow + 1; currentRow++) {
			row = getRow(sheet, currentRow);
			if (row != null) {
				int totalColumn = row.getLastCellNum();
				for (int currentColumn = 0; currentColumn < totalColumn; currentColumn++) {
					Cell cell;
					cell = row.getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
					if (cell.getCellType() == CellType.STRING) {
						return row.getRowNum();

					} else if (cell.getCellType() == CellType.NUMERIC) {
						return row.getRowNum();

					} else if (cell.getCellType() == CellType.BOOLEAN) {
						return row.getRowNum();
					} else if (cell.getCellType() == CellType.ERROR) {
						return row.getRowNum();
					}
				}
			}
		}
		return (-1);
	}

	private Row getRow(Sheet sheet, int rowNumber) {
		return sheet.getRow(rowNumber);
	}

	private LinkedHashMap<String, String> getCellValue(Sheet sheet, Row row, int currentColumn) {
		LinkedHashMap<String, String> columnMapdata = new LinkedHashMap<String, String>();
		Cell cell;
		if (row == null) {
			if (sheet.getRow(sheet.getFirstRowNum()).getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
					.getCellType() != CellType.BLANK) {
				String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(currentColumn)
						.getStringCellValue();
				columnMapdata.put(columnHeaderName, "");
			}
		} else {
			cell = row.getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
			if (cell.getCellType() == CellType.STRING) {
				if (sheet.getRow(sheet.getFirstRowNum())
						.getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
						.getCellType() != CellType.BLANK) {
					String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
							.getStringCellValue();
					columnMapdata.put(columnHeaderName, cell.getStringCellValue());
				}
			} else if (cell.getCellType() == CellType.NUMERIC) {
				if (sheet.getRow(sheet.getFirstRowNum())
						.getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
						.getCellType() != CellType.BLANK) {
					String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
							.getStringCellValue();
					columnMapdata.put(columnHeaderName, NumberToTextConverter.toText(cell.getNumericCellValue()));
				}
			} else if (cell.getCellType() == CellType.BLANK) {
				if (sheet.getRow(sheet.getFirstRowNum())
						.getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
						.getCellType() != CellType.BLANK) {
					String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
							.getStringCellValue();
					columnMapdata.put(columnHeaderName, "");
				}
			} else if (cell.getCellType() == CellType.BOOLEAN) {
				if (sheet.getRow(sheet.getFirstRowNum())
						.getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
						.getCellType() != CellType.BLANK) {
					String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
							.getStringCellValue();
					columnMapdata.put(columnHeaderName, Boolean.toString(cell.getBooleanCellValue()));
				}
			} else if (cell.getCellType() == CellType.ERROR) {
				if (sheet.getRow(sheet.getFirstRowNum())
						.getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
						.getCellType() != CellType.BLANK) {
					String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
							.getStringCellValue();
					columnMapdata.put(columnHeaderName, Byte.toString(cell.getErrorCellValue()));
				}
			}
		}
		return columnMapdata;
	}


	// Set Excel file
	public void setExcelFile(String excelPath, String sheetName) {
		Log.info("Set Excel file " + excelPath);
		Log.info("Selected Sheet: " + sheetName);

		try {
			File f = new File(excelPath);

			if (!f.exists()) {
				try {
					Log.info("File Excel path not found.");
					throw new Exception("File Excel path not found.");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (sheetName.isEmpty()) {
				try {
					Log.info("The Sheet Name is empty.");
					throw new Exception("The Sheet Name is empty.");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			fis = new FileInputStream(excelPath);
			workbook = WorkbookFactory.create(fis);
			sheet = workbook.getSheet(sheetName);
			// sh = wb.getSheetAt(0); //0 - index of 1st sheet
			if (sheet == null) {
//                sh = wb.createSheet(sheetName);
				try {
					Log.info("Sheet name not found.");
					throw new Exception("Sheet name not found.");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			excelFilePath = excelPath;

			// adding all the column header names to the map 'columns'
			sheet.getRow(0).forEach(cell -> {
				columns.put(cell.getStringCellValue(), cell.getColumnIndex());
			});

		} catch (Exception e) {
			e.getMessage();
			Log.error(e.getMessage());
		}
	}

	// Phương thức này nhận số hàng làm tham số và trả về dữ liệu của hàng đó.
	public Row getRowData(int rowNum) {
		row = sheet.getRow(rowNum);
		return row;
	}

	public Object[][] getExcelData(String excelPath, String sheetName) {
		Object[][] data = null;
		Workbook workbook = null;

		Log.info("Set Excel file " + excelPath);
		Log.info("Selected Sheet: " + sheetName);

		try {

			File f = new File(excelPath);

			if (!f.exists()) {
				try {
					Log.info("File Excel path not found.");
					throw new Exception("File Excel path not found.");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (sheetName.isEmpty()) {
				try {
					Log.info("The Sheet Name is empty.");
					throw new Exception("The Sheet Name is empty.");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// load the file
			FileInputStream fis = new FileInputStream(excelPath);

			// load the workbook
			workbook = new XSSFWorkbook(fis);
			// load the sheet
			Sheet sheet = workbook.getSheet(sheetName);
			// load the row
			Row row = sheet.getRow(0);

			int noOfRows = sheet.getPhysicalNumberOfRows();
			int noOfCols = row.getLastCellNum();

			System.out.println(noOfRows + " - " + noOfCols);

			Cell cell;
			data = new Object[noOfRows - 1][noOfCols];

			// Vòng lặp FOR chạy từ 1 để bỏ dòng tiêu đề (dòng tiêu đề là 0)
			for (int i = 1; i < noOfRows; i++) {
				for (int j = 0; j < noOfCols; j++) {
					row = sheet.getRow(i);
					cell = row.getCell(j);

					// Này dùng để xác định kiểu dữ liệu từ ô trong excel rồi chuển về String luôn
					// cho tiện đọc
					switch (cell.getCellType()) {
					case STRING:
						data[i - 1][j] = cell.getStringCellValue();
						break;
					case NUMERIC:
						data[i - 1][j] = String.valueOf(cell.getNumericCellValue());
						break;
					case BLANK:
						data[i - 1][j] = "";
						break;
					default:
						data[i - 1][j] = null;
						break;
					}
				}
			}
		} catch (Exception e) {
			e.getMessage();
			throw new RuntimeException(e);
		}
		return data;
	}

	public Object[][] getDataHashTable(String excelPath, String sheetName, int startRow, int endRow) {
		Log.info("Excel File: " + excelPath);
		Log.info("Selected Sheet: " + sheetName);

		Object[][] data = null;

		try {

			File f = new File(excelPath);

			if (!f.exists()) {
				try {
					Log.info("File Excel path not found.");
					throw new Exception("File Excel path not found.");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			fis = new FileInputStream(excelPath);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);

			int rows = getRows();
			int columns = getColumns();

			Log.info("Row: " + rows + " - Column: " + columns);
			Log.info("StartRow: " + startRow + " - EndRow: " + endRow);

			data = new Object[(endRow - startRow) + 1][1];
			Hashtable<String, String> table = null;
			for (int rowNums = startRow; rowNums <= endRow; rowNums++) {
				table = new Hashtable<>();
				for (int colNum = 0; colNum < columns; colNum++) {
					table.put(getCellData(0, colNum), getCellData(rowNums, colNum));
				}
				data[rowNums - startRow][0] = table;
			}

		} catch (IOException e) {
			e.printStackTrace();
			Log.error(e.getMessage());
		}

		return data;

	}

	public String getTestCaseName(String testCaseName) {
		String value = testCaseName;
		int position = value.indexOf("@");
		value = value.substring(0, position);
		position = value.lastIndexOf(".");

		value = value.substring(position + 1);
		return value;
	}

	public int getRowContains(String sTestCaseName, int colNum) {
		int i;
		int rowCount = getRows();
		for (i = 0; i < rowCount; i++) {
			if (getCellData(i, colNum).equalsIgnoreCase(sTestCaseName)) {
				break;
			}
		}
		return i;
	}

	public int getRows() {
		try {
			return sheet.getLastRowNum();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw (e);
		}
	}

	public int getColumns() {
		try {
			row = sheet.getRow(0);
			return row.getLastCellNum();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw (e);
		}
	}

	// Get cell data
	public String getCellData(int rowNum, int colNum) {
		try {
			cell = sheet.getRow(rowNum).getCell(colNum);
			String CellData = null;
			switch (cell.getCellType()) {
			case STRING:
				CellData = cell.getStringCellValue();
				break;
			case NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					CellData = String.valueOf(cell.getDateCellValue());
				} else {
					CellData = String.valueOf((long) cell.getNumericCellValue());
				}
				break;
			case BOOLEAN:
				CellData = Boolean.toString(cell.getBooleanCellValue());
				break;
			case BLANK:
				CellData = "";
				break;
			}
			return CellData;
		} catch (Exception e) {
			return "";
		}
	}

	public String getCellData(int rowNum, String columnName) {
		return getCellData(rowNum, columns.get(columnName));
	}

	// Write data to excel sheet
	public void setCellData(String text, int rowNumber, int colNumber) {
		try {
			row = sheet.getRow(rowNumber);
			if (row == null) {
				row = sheet.createRow(rowNumber);
			}
			cell = row.getCell(colNumber);

			if (cell == null) {
				cell = row.createCell(colNumber);
			}
			cell.setCellValue(text);

			XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
			text = text.trim().toLowerCase();
			if (text == "pass" || text == "passed" || text == "success") {
				style.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
			}
			if (text == "fail" || text == "failed" || text == "failure") {
				style.setFillForegroundColor(IndexedColors.RED.getIndex());
			}
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style.setAlignment(HorizontalAlignment.CENTER);
			style.setVerticalAlignment(VerticalAlignment.CENTER);

			cell.setCellStyle(style);

			fileOut = new FileOutputStream(excelFilePath);
			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			e.getMessage();
			Log.error(e.getMessage());
		}
	}

	public static void setCellData(String text, int rowNumber, String columnName) {
		try {
			row = sheet.getRow(rowNumber);
			if (row == null) {
				row = sheet.createRow(rowNumber);
			}
			cell = row.getCell(columns.get(columnName));

			if (cell == null) {
				cell = row.createCell(columns.get(columnName));
			}
			cell.setCellValue(text);

			XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
			text = text.trim().toLowerCase();
			if (text == "pass" || text == "passed" || text == "success") {
				style.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
			}
			if (text == "fail" || text == "failed" || text == "failure") {
				style.setFillForegroundColor(IndexedColors.RED.getIndex());
			}

			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style.setAlignment(HorizontalAlignment.CENTER);
			style.setVerticalAlignment(VerticalAlignment.CENTER);

			cell.setCellStyle(style);

			fileOut = new FileOutputStream(excelFilePath);
			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			e.getMessage();
			Log.error(e.getMessage());
		}
	}

}
