package Assertions;

import static io.restassured.RestAssured.when;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class HeaderValidation {
	@Test
	public void headerValidation() {
		Response response=when()
		   .get("http://49.249.28.218:8091/project/NH_PROJ_6245");
		response.then().assertThat().statusCode(200);
		response.then().assertThat().statusLine("HTTP/1.1 200 ");
		response.then().assertThat().contentType(ContentType.JSON);
		response.then().assertThat().header("Transfer-Encoding", "chunked");
		response.then().log().all();
		
	}

}
