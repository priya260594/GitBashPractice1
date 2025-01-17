package PostRequest;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Random;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class UsingHashMap {
	@Test
	public void createPostUsingJSONFile() {
		Random r=new Random();
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("createdBy", "Priyanka");
		map.put("projectName", "priya_HRM"+r.nextInt(1000));
		map.put("status", "Created");
		map.put("teamSize", 0);
		
		given()
	      .body(map)
	      .contentType(ContentType.JSON)
	   .when()
	      .post("http://49.249.28.218:8091/addProject")
	   .then()
	      .assertThat().statusCode(201)
	      .log().all();
		
		
	}

}
