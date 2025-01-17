package RequestChaining;

import static io.restassured.RestAssured.given;

import java.util.Random;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import PostRequest.UsingPOJOClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AddEmployeeToProject {
	@Test
	public void createProject() {
		Random ran=new Random(); 
		int r=ran.nextInt(2000);
		UsingPOJOClass obj=new UsingPOJOClass("Priya", "HRM_"+r, "Ongoing", 0);
	//API-1 createProject	
	Response res=	given()
		              .body(obj)
		              .contentType(ContentType.JSON)
		            .when() 
		              .post("http://49.249.28.218:8091/addProject");
	String projectName=res.jsonPath().get("projectName");
	res.then()
	        .log().all()
	        .assertThat().statusCode(201)
	        .assertThat().body("msg", Matchers.equalTo("Successfully Added"));
	System.out.println("The project created is "+projectName);
	
	//API-2 Add Employee
	AddEmployeePojoClass emp=new AddEmployeePojoClass("Material Engineer", "26/04/1994", "pri22@gmail.com", "User_"+r, 4, "8899664477", projectName, "ROLE_EMPLOYEE", "User_"+r);
	
	given()
	   .body(emp)
	   .contentType(ContentType.JSON)
	.when()   
	   .post("http://49.249.28.218:8091/employees")
	.then()
	    .assertThat().statusCode(201)
	    .log().all();


	}
}
