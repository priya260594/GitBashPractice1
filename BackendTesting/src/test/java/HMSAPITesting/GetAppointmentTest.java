package HMSAPITesting;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import HMS_POJOClass.BookAppointment;
import HMS_POJOClass.LoginPatient;
import fileUtility.PropertyFileUtility;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetAppointmentTest {
	PropertyFileUtility pobj = new PropertyFileUtility();
	@Test
	public void getAppointmentTest() throws IOException {
		String token = pobj.getDataFromPropertyFile("jwtToken");
		String BaseURI = pobj.getDataFromPropertyFile("BaseURI");
		String emailId=pobj.getDataFromPropertyFile("patientEmail");
		String password=pobj.getDataFromPropertyFile("patientPassword");
		
		String doctor_Spec="radiology";
		String doctor_Name="kalpana";
		String date="15-12-2024";
		String time="11:30 AM";
		
		//Login Patient
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
	   
	    //Book Appointment        
	    BookAppointment appointment=new BookAppointment(doctor_Spec, doctor_Name, date, time);
	    Response resp2=given()
	    		         .pathParam("PatientId", patientId)
	    		         .body(appointment)
	    		         .contentType(ContentType.JSON)
	    		       .when()
	    		          .post(BaseURI+"/patient/appointment/{PatientId}");
	    String bookid=resp2.jsonPath().get("BookingId");
	    resp2.then().statusCode(201).statusLine("HTTP/1.1 ").time(Matchers.lessThan(2000l))
	                .body("msg", Matchers.equalTo("Appointment successfully done"))
	                .log().all();
	    //get Appointment
	    Response res3=given()
	    		         .pathParam("BookID", bookid)
	    		         .pathParam("PatientId", patientId)
	    		      .when()
	    		         .get(BaseURI+"/patient/{PatientId}/appointment/{BookID}");    
	    res3.then().statusCode(204).statusLine("HTTP/1.1 ").time(Matchers.lessThan(2000l))
	               .body("BookID", Matchers.equalTo(bookid))
	               .body("PatientID", Matchers.equalTo(patientId))
	               .log().all();
	            

	}
}
