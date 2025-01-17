package PostRequest;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.Random;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UsingPOJOClass {

	private String createdBy;
	private String projectName;
	private String status;
	private int teamSize;

	public UsingPOJOClass() {
	}

	public UsingPOJOClass(String createdBy, String projectName, String status, int teamSize) {
		super();
		this.createdBy = createdBy;
		this.projectName = projectName;
		this.status = status;
		this.teamSize = teamSize;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTeamSize() {
		return teamSize;
	}

	public void setTeamSize(int teamSize) {
		this.teamSize = teamSize;
	}

	@Test
	public void postRequestUsingPOJOClass(){
		Random ran=new Random(); 
		UsingPOJOClass obj=new UsingPOJOClass("Priya", "HRM_"+ran.nextInt(1000), "Ongoing", 0);
		
		given()
		   .body(obj)
		   .contentType(ContentType.JSON)
	    .when()
	        .post("http://49.249.28.218:8091/addProject")
	        
	    .then()
	        .body(JsonSchemaValidator.matchesJsonSchema(new File("./src/test/resources/jsonSchema.json")))
	        .assertThat().statusCode(201)
	        .log().all();
		

	}

}
