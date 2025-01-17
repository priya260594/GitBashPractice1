package Restful_Booker_Sample;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import RestfulBookerPOJOClass.Root;
import RestfulBookerPOJOClass.bookingdates;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateBookingTest {
	@Test
	public void createBookingTest() throws Throwable {
		JSONObject obj=new JSONObject();
		obj.put("username","admin");
		obj.put("password", "password123");
		Response res=given()
		  .contentType(ContentType.JSON)
		  .body(obj)
		.when()
		   .post("https://restful-booker.herokuapp.com/auth");
		String AuthToken=res.jsonPath().get("token");
		res.then()
		    .statusCode(200)
		    .statusLine("HTTP/1.1 200 OK")
		    .log().all();	
		bookingdates bookingdates=new bookingdates("2025-01-12", "2025-09-12");
		Root root= new Root("Supriedya", "Kamal", 111, false, bookingdates, "Breakfast");
		
		
//		String reqBody="{\r\n"
//				+ "    \"firstname\" : \"Priya\",\r\n"
//				+ "    \"lastname\" : \"Mahesha\",\r\n"
//				+ "    \"totalprice\" : 935,\r\n"
//				+ "    \"depositpaid\" : true,\r\n"
//				+ "    \"bookingdates\" : {\r\n"
//				+ "        \"checkin\" : \"2024-11-01\",\r\n"
//				+ "        \"checkout\" : \"2025-03-01\"\r\n"
//				+ "    },\r\n"
//				+ "    \"additionalneeds\" : \"break\"\r\n"
//				+ "}";
		given()
		   .auth().oauth2(AuthToken)
		   .contentType(ContentType.JSON)
		   .body(root)
		   .log().all()
		.when()
		   .post("https://restful-booker.herokuapp.com/booking")
	    .then()
		    .statusCode(200)
		    .statusLine("HTTP/1.1 200 OK")
		    .log().all();
	}

}
