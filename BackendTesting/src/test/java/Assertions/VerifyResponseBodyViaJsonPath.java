 package Assertions;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.response.Response;

public class VerifyResponseBodyViaJsonPath {
	@Test 
	public void VerifyRBViaJsonPath() {
		
		Response response=given()
		                   .get("http://49.249.28.218:8091/projects-paginated");
		response.the  n().log().all();
		int data=response.jsonPath().get("numberOfElements");
		boolean ra=JsonPath.read(response.asString(),"pageable.sort.unsorted");
		System.out.println("The jsonPath class"+ra);
		System.out.println(data);
		boolean data2=response.jsonPath().get("pageable.sort.unsorted");
		System.out.println(data2);
		String data3=response.jsonPath().get("content[1].projectId");
		System.out.println(data3);
		ArrayList<String> allIds=response.jsonPath().get("content[*].projectId");
		System.out.println(allIds);
		
		response.then().header("X-Content-Type-Options", "nosniff");
		response.then().assertThat().body("numberOfElements", Matchers.greaterThanOrEqualTo(data));
		response.then().assertThat().body("pageable.sort.unsorted", Matchers.equalTo(data2));
		response.then().assertThat().body("content[1].projectId", Matchers.equalTo(data3));
		
//		System.out.println(response.jsonPath().getInt("numberOfElements"));
//		System.out.println(response.jsonPath().getBoolean("empty"));
//		System.out.println(response.jsonPath().getBoolean("pageable.sort.unsorted"));
//		System.out.println(response.jsonPath().getString("content[1].projectId"));
	}

}
