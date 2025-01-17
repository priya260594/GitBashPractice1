package com.backend.withBDD;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SampleCRUDWithBDDTest {
	@Test
	public void getDataTest() {
		when()
		   .get("http://49.249.28.218:8091/project/NH_PROJ_849082")
	   .then()
	       .assertThat().statusCode(200)
	       .log().all();
	}
     @Test
     public void createDataTest() {
 		Random r=new Random();
        JSONObject obj=new JSONObject();
		obj.put("createdBy", "Priya");
		obj.put("projectName", "Ninja_"+r.nextInt(1000));
		obj.put("status","Ongoing");
		obj.put("teamSize", 0);
		
    	
		Response res=given()
		   .contentType(ContentType.JSON)
		   .body(obj)
	   .when()
	       .post("http://49.249.28.218:8091/addProject");
		res.then()
		    .assertThat().statusCode(201)
		    .log().all()
		    .extract().body();
		String resBody=res.getBody().asString();
		try(FileWriter fileWrite=new FileWriter(new File("./src/test/resources/writeResponseBodyTojson.json"))){
			fileWrite.write(resBody);
			System.out.println("File is saved");
		}catch(Exception e) {
		System.out.println("File is saved");
		}
     }
     @Test
     public void completeDataTest() {
            JSONObject obj=new JSONObject();
    		obj.put("createdBy", "Kalyani");
    		obj.put("projectName", "Ninja_5954");
    		obj.put("status","Created");
    		obj.put("teamSize", 0);
    		
    		given()
    		   .contentType(ContentType.JSON).body(obj.toJSONString())
    		.when()
    		    .put("http://49.249.28.218:8091/project/NH_PROJ_5553")
    		.then()
    		    .assertThat().statusCode(200)
    		    .log().all();
     }
     @Test
     public void partialDataTest() {
        JSONObject obj=new JSONObject();
 		obj.put("createdBy", "Aadvik");
 		
 		given()
 		   .contentType(ContentType.JSON).body(obj.toJSONString())
 		.when()
 		   .put("http://49.249.28.218:8091/project/NH_PROJ_5553")
 		.then()
 		    .assertThat().statusCode(200)
 		    .log().all();
     }
     @Test
     public void deleteDataTest() {
    	 when()
    	     .delete("http://49.249.28.218:8091/project/NH_PROJ_5956")
        .then()
             .assertThat().statusCode(204)
             .log().all();
     }
}
