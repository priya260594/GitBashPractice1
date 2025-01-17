package typesOfParameter;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class QueryParameter {
	// acts as both search and filter the conditions
	@Test
	public void samplePathParameter() {
		given()
           .queryParam("projectId","NH_PROJ_6519")
           .log().all()
		.when()
		   .patch("http://49.249.28.218:8091/project")
		.then()
		   .log().all();
	}
}
