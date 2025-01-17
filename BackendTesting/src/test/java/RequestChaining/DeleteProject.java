package RequestChaining;
import static io.restassured.RestAssured.given;

import java.util.Random;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import PostRequest.UsingPOJOClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteProject {

	@Test
	public void deleteProject() {
		Random ran=new Random(); 
		UsingPOJOClass obj=new UsingPOJOClass("Priya", "HRM_"+ran.nextInt(1000), "Ongoing", 0);
	//API-1 createProject	
	Response res=	given()
		              .body(obj)
		              .contentType(ContentType.JSON)
		              .post("http://49.249.28.218:8091/addProject");
	String projectId=res.jsonPath().get("projectId");
	res.then()
	        .log().all()
	        .assertThat().statusCode(201)
	        .assertThat().body("msg", Matchers.equalTo("Successfully Added"));
	System.out.println("The project created is "+projectId);
	//API-2 deleteProject
	              given()
	                   .delete("http://49.249.28.218:8091/project/"+projectId)
	              .then()
	                   .assertThat().statusCode(204)
	                   .log().all();
	                   
                
	}

}
