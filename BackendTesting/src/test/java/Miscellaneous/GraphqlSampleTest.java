package Miscellaneous;

import static io.restassured.RestAssured.given;

import java.util.Random;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class GraphqlSampleTest {
	@Test
	public void graphqlTest() {
		Random r=new Random();
		int ran=r.nextInt(1000);
		String query="mutation { addProject( projectName: \"HRM_"+ran+"\", teamSize: 0, createdBy: \"Priya\", createdOn: \"2024-07-05\", status: \"Created\" ) { projectId projectName teamSize createdBy createdOn status} }";
		given()
		    .body(query)
		    .contentType(ContentType.JSON)
		.when()
		    .post("http://49.249.28.218:8091/addProject-graphql")
		.then()
		    .log().all();
		
	}

	@Test
	public void getAllProject() {
		String query="{ getAllProjects{ projectId} }";
		given()
		   .body(query)
		   .contentType(ContentType.JSON)
	    .when()
			 .post("http://49.249.28.218:8091/getAll")
	    .then()
			  .log().all();
	}
	@Test
	public void getParticularProject() {
		//Random r=new Random();
		//int ran=r.nextInt(1000);
		String query="{ findProject(projectId:\"NH_PROJ_6572\"){ projectId projectName createdBy } }";
		given()
		.body(query)
		.contentType(ContentType.JSON)
		.when()
		.post("http://49.249.28.218:8091/getProjectByProjectId")
		.then()
		.log().all();
		
	}
	@Test
	public void updateProject() {
		String query="mutation { updateProject( projectId: \"NH_PROJ_6572\" projectName: \"HRM_8888\", teamSize: 0, createdBy: \"Kalpana\", createdOn: \"2024-07-05\", status: \"Created\" ) { projectId projectName teamSize createdBy createdOn status } }";
		given()
		   .body(query)
		   .contentType(ContentType.JSON)
	    .when()
			 .post("http://49.249.28.218:8091/updateProject-graphql")
	    .then()
			  .log().all();
		
		
	}
}
