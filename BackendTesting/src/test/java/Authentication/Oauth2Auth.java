package Authentication;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Oauth2Auth {
	@Test
	public void Oauth2Test() {
		
		//API-1--get authorization
	 Response res= given()
	      .formParam("client_id", "ninza-client")
	      .formParam("client_secret", "gPQBf1Yxew5OMccMhzos1GefIyiSnXzM")
	      .formParam("grant_type", "client_credentials")
	  .when()
	      .post("http://49.249.28.218:8180/auth/realms/ninza/protocol/openid-connect/token");
	 String access_Token=res.jsonPath().get("access_token");
	 System.out.println("The access token is "+access_Token);
	 res.then()
	      .assertThat().statusCode(200)
	      .log().all();

	}

}