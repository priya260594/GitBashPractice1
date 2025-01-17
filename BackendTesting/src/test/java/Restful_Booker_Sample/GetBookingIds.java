package Restful_Booker_Sample;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class GetBookingIds {
	@Test
	public void getBookingIds() {
		when()
		   .get("https://restful-booker.herokuapp.com/booking")
		.then()
		    .statusCode(200)
		    .statusLine("HTTP/1.1 200 OK")
		    .log().all();
	}

}
