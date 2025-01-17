package HMSAPITesting;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Random;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import HMS_POJOClass.LoginPatient;
import HMS_POJOClass.PatientRegister;
import fileUtility.PropertyFileUtility;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LoginPatientTest {
	PropertyFileUtility pobj = new PropertyFileUtility();

	@Test
	public void loginPatientTest() throws IOException {
		String token = pobj.getDataFromPropertyFile("jwtToken");
		String BaseURI = pobj.getDataFromPropertyFile("BaseURI");
		int ran = new Random().nextInt(1000);
		String fullname = "prisha" + ran;
		String email = "prisha" + ran + "@gmail.com";
		String pwd = "12345R" + ran;

		// RegisterPatient
		PatientRegister register = new PatientRegister(fullname, "4, Vivekananda Nagar, RR nagar, Bengaluru",
				"Bengaluru", "Male", email, pwd);
		Response res = given().auth().oauth2(token).body(register).contentType(ContentType.JSON).when()
				.post(BaseURI + "/patient/register");

		res.then().statusCode(201).statusLine("HTTP/1.1 ").time(Matchers.lessThan(2000l)).log().all();
		String emailId = res.jsonPath().get("emailId");
		String password = res.jsonPath().getString("pwd");

		// LoginPatient
		LoginPatient loginPatient = new LoginPatient(emailId, password);
		given()
		     .body(loginPatient)
		     .contentType(ContentType.JSON)
		.when()
		     .post(BaseURI+"/patient/login")
		.then()
		     .statusCode(200).statusLine("HTTP/1.1 ").time(Matchers.lessThan(2000l))
		     .body("msg", Matchers.equalTo("successfully Added"))
		     .log().all();

	}
}
