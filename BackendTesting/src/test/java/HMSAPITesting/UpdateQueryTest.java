package HMSAPITesting;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import HMS_POJOClass.SubmitQuery;
import fileUtility.PropertyFileUtility;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateQueryTest {
	PropertyFileUtility pobj = new PropertyFileUtility();
	@Test
	public void submitQueryTest() throws IOException {
		String token = pobj.getDataFromPropertyFile("jwtToken");
		String BaseURI = pobj.getDataFromPropertyFile("BaseURI");
		//SubmitQuery
		SubmitQuery query=new SubmitQuery("Priya", "priya@gmail.com", "89909377848", "This is for testing purpose");
		Response res=given()
		    .body(query)
		    .contentType(ContentType.JSON)
		.when()
		    .post(BaseURI+"/contact/submitQuery");
		String queryID=res.jsonPath().get("QueryID");
		res.then()
		     .statusCode(201).statusLine("HTTP/1.1 ").time(Matchers.lessThan(2000l))
		     .body("msg", Matchers.equalTo("Query successfully submitted"))
		     .log().all();
		//updateQuery
		SubmitQuery query2=new SubmitQuery("Priya", "priya@gmail.com", "89909377848", "This is not only for testing purpose");
		given()
		    .pathParam("queryid", queryID)
		.when()
		    .put(BaseURI+"/contact/submitQuery/{queryid}")
		.then()
		     .statusCode(200).statusLine("HTTP/1.1 ").time(Matchers.lessThan(2000l))
		     .body("msg", Matchers.equalTo("Query Successfully updated"))
		     .log().all();
		
	}


}
