package typesOfParameter;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;
public class PathParameter {
	@Test
	public void samplePathParameter() {
		given()
           .pathParam("projectId", "NH_PROJ_6519")
		.when()
		   .get("http://49.249.28.218:8091/project/{projectId}")
		.then()
		   .log().all()
		   .statusCode(200);
	}

}
