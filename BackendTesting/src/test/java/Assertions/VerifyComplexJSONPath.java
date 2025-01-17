package Assertions;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.response.Response;

public class VerifyComplexJSONPath {
	@Test
	public void verifyComplexJSONPath() {

		Response response=given()
		                   .get("http://49.249.28.218:8091/projects-paginated");
		response.then().log().all(); 
		List<String> list=JsonPath.read(response.asString(), ".content[*].projectId");
		for(String data:list) {
			System.out.println(data);
		}
		
		List<String> list1=JsonPath.read(response.asString(), ".content[*].[?(@.projectName=='fbi5')].projectId");
		String actualProjectId=list1.get(0);
		System.out.println(actualProjectId);
		Assert.assertEquals(actualProjectId, "TY_Proj_2127");
		//After extracting the data ,---> use for db validatiaon or Assertion and for Request chaining
	}

}
