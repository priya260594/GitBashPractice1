package PostRequest;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileNotFoundException;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class UsingJSONFile {
	@Test
	public void createPostViaJSONFile() throws FileNotFoundException {
		File file= new File("./PostRequestViaJSONFile.json");
			
		given()
		    .body(file)
		    .contentType(ContentType.JSON)
		.when()
		    .post("http://49.249.28.218:8091/addProject")
		.then()
		    .assertThat().statusCode(201)
		    .log().all();
	}

}
