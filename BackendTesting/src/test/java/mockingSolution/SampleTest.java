package mockingSolution;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;

public class SampleTest {
	public static void main(String args[]) {
		String reqBody="{\r\n"
				+ "\"creditcard\":\"1234567891236\",\r\n"
				+ "\"cvv\":\"123\",\r\n"
				+ "\"cardName\":\"priya\"}";
		given()
		     .body(reqBody)
		  //   .contentType(ContentType.JSON)
		.when()
		     .post("http://localhost:8889/credit-card")
		.then()
		     .log().all();
	}
}
