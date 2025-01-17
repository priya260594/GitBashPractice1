package Restful_Booker_Sample;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

public class GetBooking {
//248
	@Test
	public void getparticularBookingIds() {
		when()
		   .get("https://restful-booker.herokuapp.com/booking/248")
		.then()
		    .statusCode(200)
		    .statusLine("HTTP/1.1 200 OK")
		    .log().all();
	}
}
