package PostRequest;

import static io.restassured.RestAssured.given;

import java.util.Random;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class UsingJSONObject {

	@Test
	public void createPostUsingJSONObj(){
		Random r=new Random();
		JSONObject obj=new JSONObject();
		obj.put("createdBy", "Priyanka");
		obj.put("projectName", "priya_HRM41"+r.nextInt(1000));
		obj.put("status", "Created");
		obj.put("teamSize", 0);
		
		given()
		    .body(obj)
		    .contentType(ContentType.JSON)
		.when()
		    .post("http://49.249.28.218:8091/addProject")
		.then()
		    .assertThat().statusCode(201)
		    .log().all();

	}

}
