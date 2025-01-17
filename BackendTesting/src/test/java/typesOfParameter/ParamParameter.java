package typesOfParameter;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class ParamParameter {
	
	//For post()---> acts as form parameter & 
	  //get()-----> acts as query parameter
	@Test
	public void samplePathParameter() {
		given()
           .param("projectId","NH_PROJ_6519")
           .log().all()
		.when()
		   .post("http://49.249.28.218:8091/project")
		.then()
		   .log().all();
	}

}
