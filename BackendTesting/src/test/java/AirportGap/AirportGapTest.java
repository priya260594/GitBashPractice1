package AirportGap;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class AirportGapTest {
	String airportId,token;
	String favoriteId;
	@Test
	public void getAllAirportsTest() {
		Response res=when()
		    .get("https://airportgap.com/api/airports");
		res.then()
		    .statusCode(200)
		    .log().all();
		airportId=res.jsonPath().get("data[6].id");
		System.out.println("The airport id is "+airportId);
	}
	@Test(dependsOnMethods = "getAllAirportsTest")
	public void getParticularAirportsByIdTest() {
		Response res=when()
			    .get("https://airportgap.com/api/airports");
			res.then()
			    .statusCode(200)
			    .log().all();
	}
	@Test
	public void createAirportDistanceTest() {
		given()
		    .queryParam("from", "KIX")
		    .queryParam("to", "NRT")
		.when()
		    .post("https://airportgap.com/api/airports/distance")
		.then()
		    .statusCode(200)
		    .log().all();
	}
	@Test
	public void tokensTest() {
	Response res=given()
	    .formParam("email", "test@airportgap.com")
	    .formParam("password", "airportgappassword")
	.when()
	    .post("https://airportgap.com/api/tokens");
	res.then()
	    .statusCode(200)
	    .log().all();
	token=res.jsonPath().get("token");
	}
	
	@Test(dependsOnMethods = {"tokensTest","getAllAirportsTest"})
	public void postToFavoritesTest() {
		given()
		     .auth().oauth2(token)
		     .param("airport_id", airportId)
		     //.queryParam("note", "My usual layover when visiting family")
		.when()
		     .post("https://airportgap.com/api/favorites")
		.then()
		     .statusCode(201)
		     .log().all();
	}
	
	@Test(dependsOnMethods = "tokensTest")
	public void getAllFavoritesTest() {
	Response res=given()
			.auth().oauth2(token)
	.when()
	    .get("https://airportgap.com/api/favorites");
	res.then()
	    .statusCode(200)
	    .log().all();
	favoriteId=res.jsonPath().get("data[2].id");
	System.out.println("The favorite id is "+favoriteId);
	}
	
	@Test(dependsOnMethods = {"tokensTest","getAllFavoritesTest"})
	public void getParticularFavoritesByIdTest() {
		given()
		     .auth().oauth2(token)
		     .param("favoriteID", favoriteId)
		.when()
		      .get("https://airportgap.com/api/favorites")
		.then()
		      .statusCode(200)
		      .log().all();	
	}
	@Test(dependsOnMethods = {"tokensTest","getAllAirportsTest"})
	public void updateFavorite() {
		given()
		     .auth().oauth2(token)
		     .pathParam("airportID", airportId)
		   //  .queryParam("note", "A note you want to save about the airport.")
		.when()
		       .patch("https://airportgap.com/api/airports/{airportID}")	
		.then()
		       .statusCode(200)
		       .log().all();
		    
	}
	@Test
	public void deleteParticularFavoriteTest() {
		given()
		   .auth().oauth2(token)
		   .param("airportId", airportId)
		.when()
		   .delete("https://airportgap.com/api/favorites/{airportId}")
		.then() 
		    .statusCode(204)
		    .log().all();
		
	}
	@Test
	public void deleteAllFavoriteTest() {
		
	}
}
