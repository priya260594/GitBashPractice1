package HMSAPITesting;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import HMS_POJOClass.LoginPatient;
import HMS_POJOClass.PatientRegister;
import fileUtility.PropertyFileUtility;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Sample_PatientTest {
	PropertyFileUtility pobj = new PropertyFileUtility();
	List<String> emailIds;
	List<String> passwords;

	@Test(invocationCount = 10)
	public void registerPatientTest() throws IOException {
		int ran = new Random().nextInt(1000);
		String fullname = "prisha" + ran;
		String email = "prisha" + ran + "@gmail.com";
		String pwd = "12345R" + ran;

		PatientRegister register = new PatientRegister(fullname, "4, Vivekananda Nagar, RR nagar, Bengaluru",
				"Bengaluru", "Male", email, pwd);
		Response res = given().auth().oauth2(pobj.getDataFromPropertyFile("jwtToken")).body(register)
				.contentType(ContentType.JSON).when()
				.post("" + pobj.getDataFromPropertyFile("BaseURI") + "/patient/register");

		emailIds.add(email);
		passwords.add(pwd);

		res.then().statusCode(201).statusLine("HTTP/1.1 ").time(Matchers.lessThan(2000l))
				.body("fullName", Matchers.equalTo(fullname)).body("gender", Matchers.equalTo("Male"))
				.body("emailId", Matchers.equalTo(email)).body("password", Matchers.equalTo(pwd)).log().all();
	}

	@Test(dependsOnMethods = "registerPatientTest")
	public void loginPatientTest() throws IOException {
		for (String emailId : emailIds) {
			for (String password : passwords) {
				LoginPatient login = new LoginPatient(emailId, password);

				Response res = given().auth().oauth2(pobj.getDataFromPropertyFile("jwtToken")).body(login)
						.contentType(ContentType.JSON)

						.when().post("" + pobj.getDataFromPropertyFile("BaseURI") + "/patient/login");

				res.then().statusCode(201).statusLine("HTTP/1.1 ").time(Matchers.lessThan(2000l));

			}
		}
	}
}
