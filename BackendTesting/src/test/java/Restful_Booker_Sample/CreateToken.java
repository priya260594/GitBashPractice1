package Restful_Booker_Sample;

import static io.restassured.RestAssured.*;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateToken {
	String AuthToken;
	@Test
	public void creatingNewTokenTest() {
		JSONObject obj=new JSONObject();
		obj.put("username","admin");
		obj.put("password", "password123");
		Response res=given()
		  .contentType(ContentType.JSON)
		  .body(obj)
		.when()
		   .post("https://restful-booker.herokuapp.com/auth");
		AuthToken=res.jsonPath().get("token");
		res.then()
		    .statusCode(200)
		    .statusLine("HTTP/1.1 200 OK")
		    .log().all();	
		
	}
	int bookingID;
	@Test
	public void createBookingTest() {
		String reqBody="{\r\n"
				+ "    \"firstname\" : \"Priya\",\r\n"
				+ "    \"lastname\" : \"Mahesha\",\r\n"
				+ "    \"totalprice\" : 935,\r\n"
				+ "    \"depositpaid\" : true,\r\n"
				+ "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2024-11-01\",\r\n"
				+ "        \"checkout\" : \"2025-03-01\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\" : \"Breakfast\"\r\n"
				+ "}";
		Response res=given()
		   .contentType(ContentType.JSON)
		   .body(reqBody)
		.when()
		   .post("https://restful-booker.herokuapp.com/booking");
		res.then()
		    .statusCode(200)
		    .statusLine("HTTP/1.1 200 OK")
		    .log().all();
	 bookingID=res.jsonPath().get("bookingid");
	 System.out.println("======================The Booking id is "+bookingID+"=======================");
	}
	
	@Test(dependsOnMethods ={"creatingNewTokenTest", "createBookingTest"})
	public void completeUpdateBookingTest() {
		System.out.println("======================The Booking id is "+bookingID+"=======================");
		String reqBody2="{\r\n"
				+ "    \"firstname\" : \"Kalpana\",\r\n"
				+ "    \"lastname\" : \"Krishna\",\r\n"
				+ "    \"totalprice\" : 335,\r\n"
				+ "    \"depositpaid\" : false,\r\n"
				+ "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2023-11-01\",\r\n"
				+ "        \"checkout\" : \"2024-03-01\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\" : \"Breakfast\"\r\n"
				+ "}";
		given()
		      .cookie("token",""+AuthToken+"")
		      .pathParam("bookingId", bookingID)
		      .contentType(ContentType.JSON)
		      .body(reqBody2)
		.when()
		      .put("https://restful-booker.herokuapp.com/booking/{bookingId}")
		.then()
		      .statusCode(200)
		      .contentType(ContentType.JSON)
		      .log().all();
		/**********************Check with Get method***************************/
		given()
		     .pathParam("bookingId", bookingID)
		.when()
		     .get("https://restful-booker.herokuapp.com/booking/{bookingId}")
		.then()
		     .statusCode(200)
		     .statusLine("HTTP/1.1 200 OK")
		     .log().all();
	}
	
	@Test(dependsOnMethods ={"creatingNewTokenTest", "createBookingTest"})
	public void partialUpdateBookingTest() {
		System.out.println("======================The Booking id is "+bookingID+"=======================");
		String reqBody2="{\r\n"
				+ "    \"firstname\" : \"Shanthi\",\r\n"
				+ "    \"lastname\" : \"Boopathi\"\r\n"
				+ "}";
		given()
		      .cookie("token",""+AuthToken+"")
		      .pathParam("bookingId", bookingID)
		      .contentType(ContentType.JSON)
		      .body(reqBody2)
		.when()
		      .patch("https://restful-booker.herokuapp.com/booking/{bookingId}")
		.then()
		      .statusCode(200)
		      .statusLine("HTTP/1.1 200 OK")
		      .contentType(ContentType.JSON)
		      .log().all();
		/**********************Check with Get method***************************/
		given()
		     .pathParam("bookingId", bookingID)
		.when()
		     .get("https://restful-booker.herokuapp.com/booking/{bookingId}")
		.then()
		     .statusCode(200)
		     .log().all();
	}
	
	@Test(dependsOnMethods ={"creatingNewTokenTest", "createBookingTest"})
	public void deleteBookingTest() {
		System.out.println("======================The Booking id is "+bookingID+"=======================");
		given()
		     .cookie("token",AuthToken)
		     .pathParam("bookingId", bookingID)
	    .when()
	         .delete("https://restful-booker.herokuapp.com/booking/{bookingId}")
	    .then()
	         .statusCode(201)
	         .statusLine("HTTP/1.1 201 Created")
	         .log().all();
		System.out.println("BOOKING ID:"+bookingID+" is successfully deleted");
		/**********************Check with Get method***************************/
		given()
		     .pathParam("bookingId", bookingID)
		.when()
		     .get("https://restful-booker.herokuapp.com/booking/{bookingId}")
		.then()
		     .statusCode(404)
		     .log().all();
	}
	

}
	