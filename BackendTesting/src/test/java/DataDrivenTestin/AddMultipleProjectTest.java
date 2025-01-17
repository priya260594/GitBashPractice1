package DataDrivenTestin;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import fileUtility.ExcelUtil;
import io.restassured.http.ContentType;

public class AddMultipleProjectTest {
	@Test(dataProvider = "getDataForProject")
	public void sampleDataDrivenTesting(String createdBy,String projectName, String status, String teamSize) {
	
		String st="{\r\n"
				+ "  \"createdBy\": \""+createdBy+"\",\r\n"
				+ "  \"projectName\": \""+projectName+"\",\r\n"
				+ "  \"status\": \""+status+"\",\r\n"
				+ "  \"teamSize\": "+teamSize+"\r\n"
				+ "}";
		given()
		    .body(st).contentType(ContentType.JSON)
        .when()
            .post("http://49.249.28.218:8091/addProject")  
        .then()
            .statusCode(201)
            .log().all();
	}
	
	@DataProvider
	public Object[][] getDataForProject() throws EncryptedDocumentException, IOException {
		ExcelUtil eUtil=new ExcelUtil();
//		int rowCount=eUtil.getRowCount("Sheet1","ProjectTestData");
//		Object[][] objArr=new Object[rowCount][4];
//		
//		for(int i=0;i<rowCount;i++) {
//			objArr[i][0]=eUtil.getDataFromExcelFile("ProjectTestData", "Sheet1", i+1, 0);
//			objArr[i][1]=eUtil.getDataFromExcelFile("ProjectTestData", "Sheet1", i+1, 1);
//			objArr[i][2]=eUtil.getDataFromExcelFile("ProjectTestData", "Sheet1", i+1, 2);
//			objArr[i][3]=eUtil.getDataFromExcelFile("ProjectTestData", "Sheet1", i+1, 3);
//		}
		return eUtil.readMultipleDataFromExcel("Sheet1", "ProjectTestData");
	//	Object[][] objArr=new Object[3][4];
//		objArr[0][0]="Priya";
//		objArr[0][1]="HRM_415";
//		objArr[0][2]="Created";
//		objArr[0][3]=0;
//		
//		objArr[1][0]="Priya";
//		objArr[1][1]="HRM_425";
//		objArr[1][2]="Ongoing";
//		objArr[1][3]=0;
//		
//		objArr[2][0]="Priya";
//		objArr[2][1]="HRM_345";
//		objArr[2][2]="Completed";
//		objArr[2][3]=0;
       // return objArr;
	}

}
