package httpBin;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.annotations.Test;

public class BasicAuth {
	@Test
	public void basicAuthTest() {
		given()
		     .auth().basic("pro", "prod@")
		.when()
		      .get("https://httpbin.org/basic-auth/pro/prod@")
		.then()
		      .statusCode(200)
		      .log().all();
	}
	@Test
	public void bearerTest() {
		given()
		.auth().oauth2("PLAne@")
		.when()
		.get("https://httpbin.org/bearer")
		.then()
		.log().all();
	}
	@Test
	public void DigestWithoutStaleTest() {
		given()
		.auth().digest("user", "passwd")
		.when()
		.get("https://httpbin.org/digest-auth/qop/user/passwd/algorithm")
		.then()
		.log().all();
	}
	@Test
	public void DigestWithStaleTest() {
		given()
		.auth().digest("user", "passwd")
		.when()
		.get("https://httpbin.org/digest-auth/auth/user/passwd/MD5/never")
		.then()
		.log().all();
	}
	
	@Test
	public void hiddenBasicAuthTest() {
		given()
		     .auth().preemptive().basic("projt", "prot@")
		.when()
		      .get("https://httpbin.org/hidden-basic-auth/projt/prot@")
		.then()
		      .statusCode(200)
		      .log().all();
	}

}
