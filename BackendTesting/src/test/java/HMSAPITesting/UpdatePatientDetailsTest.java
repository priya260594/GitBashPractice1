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

public class UpdatePatientDetailsTest {
	PropertyFileUtility pobj = new PropertyFileUtility();

	@Test
	public void updatePatientDetailsTest() throws IOException {
		String token = pobj.getDataFromPropertyFile("jwtToken");
		String BaseURI = pobj.getDataFromPropertyFile("BaseURI");
		int ran = new Random().nextInt(1000);
		String fullname = "Ravi" + ran;
		String address="4, Vivekananda Nagar, RR nagar, Bengaluru";
		String city="Bengaluru";
		String gender="Female";
		String email = "Ravi" + ran + "@gmail.com";
		String pwd = "12345R" + ran;

		// RegisterPatient
		PatientRegister register = new PatientRegister(fullname, address,
				city, gender, email, pwd);
		Response res = given().auth().oauth2(token).body(register).contentType(ContentType.JSON).when()
				.post(BaseURI + "/patient/register");

		res.then().statusCode(201).statusLine("HTTP/1.1 ").time(Matchers.lessThan(2000l)).log().all();
		String patientId = res.jsonPath().get("patientId");
		
		//updatePatient
		
		String address2="6,Ambethkar street, kannadasan nagar, Bengaluru" ;
		
		PatientRegister register2 = new PatientRegister(fullname, address2,
				city, gender, email, pwd);
		
		Response res2 = given()
				           .auth().oauth2(token)
				           .body(register)
				           .contentType(ContentType.JSON)
				           .param("patientId", patientId)
				       .when()
				           .post(BaseURI + "/patient/{patientId}");
        res2.then()
		        .statusCode(200).statusLine("HTTP/1.1 ")
		        .time(Matchers.lessThan(2000l)).log().all();
	}

}
