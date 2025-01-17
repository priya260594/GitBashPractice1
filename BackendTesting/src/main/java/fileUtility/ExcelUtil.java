package fileUtility;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	public String getDataFromExcelFile(String fileName,String sheetName,int row, int cell) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("./src/test/resources/"+fileName+".xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet(sheetName);
		Row ro=sh.getRow(row);
		String data=ro.getCell(cell).getStringCellValue();
		wb.close();
		return data;
	}
	
	public int getRowCount(String sheetName,String fileName) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("./src/test/resources/"+fileName+".xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet(sheetName);
		int lastRowNum=sh.getLastRowNum();
		wb.close();
		return lastRowNum;
	}
	
	public int getCellCount(String sheetName) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("./src/test/resources/TestScriptData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet(sheetName);
	//sh.getRow(0).getPhysicalNumberOfCells();
		int lastCellNum=sh.getRow(0).getLastCellNum();
		wb.close();
		return lastCellNum;
	}
	
	public Object[][] readMultipleDataFromExcel(String sheetName,String filename) throws EncryptedDocumentException, IOException{
		FileInputStream fis=new FileInputStream("./src/test/resources/"+filename+".xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet(sheetName);
		int lastRowNum=sh.getLastRowNum();
		int lastCellNum=sh.getRow(0).getLastCellNum();
		
		Object[][] data=new Object[lastRowNum][lastCellNum];
				for(int i=0;i<lastRowNum;i++) {
					for(int j=0;j<lastCellNum;j++) {
						data[i][j]=sh.getRow(i+1).getCell(j).getStringCellValue();
					}
				}
		
		return data;
	}
		
		public void writeBackMultipleDataToExcel(String shName,Object[][] data) throws EncryptedDocumentException, IOException   {
			FileInputStream fis=new FileInputStream("./src/test/resources/myExcel.xlsx");
			Workbook wb=WorkbookFactory.create(fis);
			Sheet sh=wb.createSheet(shName);
			for(int i=0;i<data.length;i++) {
				Row row=sh.createRow(i);
				for(int j=0;j<data[i].length;j++) {
					row.createCell(j).setCellValue(data[i][j].toString());
				}
			}
			
			FileOutputStream fos=new FileOutputStream("./src/test/resources/myExcel.xlsx");
			wb.write(fos);
			wb.close();
		}
}
