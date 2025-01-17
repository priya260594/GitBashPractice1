package HMSAPITesting;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.Random;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import HMS_POJOClass.PatientRegister;
import fileUtility.PropertyFileUtility;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeletePatientTest {
	PropertyFileUtility pobj = new PropertyFileUtility();
	@Test
	public void deletePatientTest() throws IOException {
		String token=pobj.getDataFromPropertyFile("jwtToken");
		String BaseURI =pobj.getDataFromPropertyFile("BaseURI");
		int ran = new Random().nextInt(1000);
		String fullname = "prisha" + ran;
		String email = "prisha" + ran + "@gmail.com";
		String pwd = "12345R" + ran;
       //register patient
		PatientRegister register = new PatientRegister(fullname, "4, Vivekananda Nagar, RR nagar, Bengaluru",
				"Bengaluru", "Male", email, pwd);
		Response res = given().auth().oauth2(token).body(register)
				.contentType(ContentType.JSON).when()
				.post(BaseURI+"/patient/register");
		String patientID=res.jsonPath().get("PatientID");

		res.then().statusCode(201).statusLine("HTTP/1.1 ").time(Matchers.lessThan(2000l))
				.body("fullName", Matchers.equalTo(fullname)).body("gender", Matchers.equalTo("Male"))
				.body("emailId", Matchers.equalTo(email)).body("password", Matchers.equalTo(pwd)).log().all();
		//delete patient
		given()
		    .pathParam("patientId", patientID)
		.when()
		    .delete(BaseURI+"/patient/{patientId}")
		.then()
		    .statusCode(200).statusLine("HTTP/1.1 ").time(Matchers.lessThan(2000l))
		    .body("msg", Matchers.equalTo("patient deleted successfully"))
		    .log().all();
		
	}

}
