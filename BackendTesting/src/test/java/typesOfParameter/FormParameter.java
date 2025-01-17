package typesOfParameter;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class FormParameter {
	//avoid usage of Key&value in uri instead it will pass the details in request body itself
	//for authentication purpose we will use this
	@Test
	public void samplePathParameter() {
		given()
           .formParam("projectId","NH_PROJ_6519")
           .log().all()
		.when()
		   .post("http://49.249.28.218:8091/project")
		.then()
		   .log().all();
	}

}
