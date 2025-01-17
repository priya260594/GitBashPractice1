package Miscellaneous;

import static io.restassured.RestAssured.given;

import java.io.File;

public class UploadImgTest {

	public static void main(String[] args) {
		given().multiPart(new File("./src/test/resources/MultiPartFile.png"))
    	.when().get("http://postman-echo.com/post")
		.then().log().all();
	}
}
