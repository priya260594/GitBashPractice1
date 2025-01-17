package Assertions;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.Random;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class JSONSchemaValidation {
	@Test
	public void jsonSchemaValidation() {
		Random r=new Random();
		JSONObject obj=new JSONObject();
		obj.put("createdBy", "Priyanka");
		obj.put("projectName", "priya_HRM61"+r.nextInt(1000));
		obj.put("status", "Created");
		obj.put("teamSize", 0);
		
		Response resp=given()
			.body(obj)
			.contentType(ContentType.JSON)
		    .post("http://49.249.28.218:8091/addProject");
		resp.then()
		    .body(JsonSchemaValidator.matchesJsonSchema(new File("./src/test/resources/jsonSchema.json")))
		    .log().all();
		    
		
	}

}
   