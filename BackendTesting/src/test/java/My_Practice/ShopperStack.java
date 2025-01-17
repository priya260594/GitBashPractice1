package My_Practice;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ShopperStack {
	@Test
	public void loginAsShopper() {
		JSONObject obj=new JSONObject();
		obj.put("email", "priyankaboopu@gmail.com");
		obj.put("password","Plane@100");
		obj.put("role", "SHOPPER");
		
	Response res=	given()
		    .body(obj)
		    .contentType(ContentType.JSON)
		.when()
		     .post("https://www.shoppersstack.com/shopping/users/login");
	String token=res.jsonPath().get("data.jwtToken");
		res.then()
		     .log().all()
		     .assertThat().statusCode(200);
		
		System.out.println("The token is "+token);
	}

}
