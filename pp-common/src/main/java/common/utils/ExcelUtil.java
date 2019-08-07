package common.utils;// package com.project.utils;

//
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//public class ExcelUtil {
//	public static void createSheet(Sheet sheet, int startLine, LinkedHashMap<String, String> title, List<Map> data) {
//		for (Map map : data) {
//			Row row = sheet.createRow(startLine);
//
//			int i = 0;
//
//			for (String key : title.keySet()) {
//				Cell cell = row.createCell(i);
//
//				cell.setCellValue(map.get(title.get(key)).toString());
//
//				i++;
//			}
//
//			startLine++;
//		}
//	}
//
//	public static Sheet createSheet(XSSFWorkbook workbook, String sheetName) {
//		return sheetName == null || "".equals(sheetName) ? workbook.createSheet() : workbook.createSheet(sheetName);
//	}
//
//	public static void main(String[] args) throws IOException {
//		FileOutputStream outputStream = new FileOutputStream("11.xlsx");
//		XSSFWorkbook workBook = new XSSFWorkbook();
//
//		Sheet sheet = createSheet(workBook, "yupan");
//
//		String jsonString = "[{\"id\":\"1\"},{\"id\":\"2\"}]";
//
//		List<Map> beanList = JSONUtil.jsonToList(jsonString, Map.class);
//		LinkedHashMap<String, String> title = new LinkedHashMap<>();
//		title.put("id", "id");
//		createSheet(sheet, 0, title, beanList);
//		workBook.write(outputStream);
//		outputStream.close();
//		workBook.close();
//		System.out.println(beanList);
//	}
//}