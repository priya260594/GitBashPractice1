package com.backend.withoutBDD;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SampleCRUDWithoutBDDTest {
	@Test
	public void getDataFromServer() {
		Response res=RestAssured.get("http://49.249.28.218:8091/project/NH_PROJ_5944");
		System.out.println(res.prettyPrint());
		//res.then().assertThat().statusCode(200);
		//res.then().log().all();		
	}

	@Test
	public void readDataFromServer() {
		Random r=new Random();
        JSONObject obj=new JSONObject();
		obj.put("createdBy", "Priya");
		obj.put("projectName", "Ninja_"+r.nextInt(1000));
		obj.put("status","Ongoing"); 
		obj.put("teamSize", 0);
		
		RequestSpecification reqSpec=RestAssured.given();
		reqSpec.body(obj.toJSONString());
		reqSpec.contentType(ContentType.JSON);
		
		Response res=reqSpec.post("http://49.249.28.218:8091/addProject");
		//System.out.println(res.asPrettyString());
		res.then().log().all();
		//res.then().assertThat().statusCode(201);
	
	}
	
	@Test
	public void updateCompleteDataInServer() {
        JSONObject obj=new JSONObject();
		obj.put("createdBy", "Priya");
		obj.put("projectName", "HRM_275");
		obj.put("status","Ongoing");
		obj.put("teamSize", 0);
		RequestSpecification reqSpec=RestAssured.given();
		reqSpec.body(obj.toJSONString());
		reqSpec.contentType(ContentType.JSON);
		
		Response res=reqSpec.put("http://49.249.28.218:8091/project/NH_PROJ_5553");
		res.then().log().all();
		res.then().assertThat().statusCode(200);	
	}
	@Test
	public void updatePartialDataInServer() {
	      JSONObject obj=new JSONObject();
			obj.put("createdBy", "Kallu");
			obj.put("status","Created");
	
			RequestSpecification reqSpec=RestAssured.given();
			reqSpec.body(obj.toJSONString());
			reqSpec.contentType(ContentType.JSON);
			Response res=reqSpec.patch("http://49.249.28.218:8091/project/NH_PROJ_5923");
			res.then().log().all();
			res.then().assertThat().statusCode(200);	
		
	}
	@Test
	public void deleteProject() {
		Response res=RestAssured.delete("http://49.249.28.218:8091/project/NH_PROJ_5926");
		res.then().log().all();
		System.out.println("The content type is "+res.getContentType());
		System.out.println("The session id is "+res.getSessionId());
		System.out.println("The response time is "+res.getTimeIn(TimeUnit.MILLISECONDS));
		System.out.println(""+res.getTime());
		res.then().assertThat().statusCode(204);
		
	}
	
}
