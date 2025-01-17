package HMSAPITesting;

import static io.restassured.RestAssured.*;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import HMS_POJOClass.BookAppointment;
import HMS_POJOClass.LoginPatient;
import fileUtility.PropertyFileUtility;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BookAppointmentTest {
	PropertyFileUtility pobj = new PropertyFileUtility();
	@Test
	public void bookAppointmentTest() throws IOException {
		String token = pobj.getDataFromPropertyFile("jwtToken");
		String BaseURI = pobj.getDataFromPropertyFile("BaseURI");
		String emailId=pobj.getDataFromPropertyFile("patientEmail");
		String password=pobj.getDataFromPropertyFile("patientPassword");
		
		String doctor_Spec="radiology";
		String doctor_Name="Priyanka";
		String date="30-11-2024";
		String time="10:15 AM";
		
		LoginPatient loginPatient = new LoginPatient(emailId, password);
		Response resp=given()
		                 .auth().oauth2(token)
		                 .body(loginPatient)
		                 .contentType(ContentType.JSON)
	            	.when()
		                 .post(BaseURI+"/patient/login");
		String patientId=resp.jsonPath().get("PatientId");
	            resp.then()
		                 .statusCode(200).statusLine("HTTP/1.1 ").time(Matchers.lessThan(2000l))
		                 .body("msg", Matchers.equalTo("successfully Added"))
		                 .log().all();
	    BookAppointment appointment=new BookAppointment(doctor_Spec, doctor_Name, date, time);
	    Response resp2=given()
	    		         .pathParam("PatientId", patientId)
	    		         .body(appointment)
	    		         .contentType(ContentType.JSON)
	    		       .when()
	    		          .post(BaseURI+"/patient/appointment/{PatientId}");
	    resp2.then().statusCode(201).statusLine("HTTP/1.1 ").time(Matchers.lessThan(2000l))
	                .body("msg", Matchers.equalTo("Appointment successfully done"))
	                .log().all();
	            

	}
}
