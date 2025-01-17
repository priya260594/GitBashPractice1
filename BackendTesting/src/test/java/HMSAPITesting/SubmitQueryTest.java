package HMSAPITesting;

import java.io.IOException;
import static io.restassured.RestAssured.*;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import HMS_POJOClass.SubmitQuery;
import fileUtility.PropertyFileUtility;
import io.restassured.http.ContentType;

public class SubmitQueryTest {
	PropertyFileUtility pobj = new PropertyFileUtility();
	@Test
	public void submitQueryTest() throws IOException {
		String token = pobj.getDataFromPropertyFile("jwtToken");
		String BaseURI = pobj.getDataFromPropertyFile("BaseURI");
		SubmitQuery query=new SubmitQuery("Priya", "priya@gmail.com", "89909377848", "This is for testing purpose");
		given()
		    .body(query)
		    .contentType(ContentType.JSON)
		.when()
		    .post(BaseURI+"/contact/submitQuery")
		.then()
		     .statusCode(201).statusLine("HTTP/1.1 ").time(Matchers.lessThan(2000l))
		     .body("msg", Matchers.equalTo("Query successfully submitted"))
		     .log().all();
	}

}
