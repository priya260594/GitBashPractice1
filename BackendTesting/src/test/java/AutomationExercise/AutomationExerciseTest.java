package AutomationExercise;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import java.util.HashMap;
import java.util.Random;

import org.testng.annotations.Test;
public class AutomationExerciseTest {
	@Test
	public void getAllProductsTest() {
		when()
		    .get("https://automationexercise.com/api/productsList")
		.then()
		     .log().all();
	}
	@Test
	public void postToAllProductsList() {
		when()
		    .post("https://automationexercise.com/api/productsList")
		.then()
		     .log().all();
	}
	@Test
	public void getAllBrandsListTest() {
		when()
		    .get("https://automationexercise.com/api/brandsList")
		.then()
		     .log().all();
	}
	@Test
	public void putToAllBrandsListTest() {
		when()
		    .put("https://automationexercise.com/api/productsList")
		.then()
		     .log().all();
	}
	@Test
	public void postToSearchProductTest() {
		given()
		    .param("search_product", "tshirt")
		.when()
		    .post("https://automationexercise.com/api/searchProduct")
		.then()
		     .log().all();
	}
	@Test
	public void postToSearchProductWithoutParameterTest() {
    when()
	    .post("https://automationexercise.com/api/searchProduct")
	.then()
	     .log().all();
	}
	@Test
	public void postToVerifyLoginWithValidDetails() {
	given()
	    .param("email", "priyanka563@gmail.com")
	    .param("password", "Plane@1")
    .when()
	    .post("https://automationexercise.com/api/verifyLogin")
	.then()
	     .log().all();
	}
	@Test
	public void postToVerifyLoginWithoutEmail() {
	given()
	    .param("password", "Plane@1")
    .when()
	    .post("https://automationexercise.com/api/verifyLogin")
	.then()
	     .log().all();
	}
	@Test
	public void deleteToVerifyLogin() {
    when()
	    .delete("https://automationexercise.com/api/verifyLogin")
	.then()
	     .log().all();
	}
	@Test
	public void postToVerifyLoginWithInvalidDetails() {
	given()
	    .param("email", "priyanka563@gmail.com")
	    .param("password", "Pla45e@1")
    .when()
	    .post("https://automationexercise.com/api/verifyLogin")
	.then()
	     .statusCode(404)
	     .log().all();
	}
	@Test
	public void createUserAccount() {
		int r=new Random().nextInt();
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("name", "Priyanka");
		map.put("email", "priyanka219@gmail.com");
		map.put("password", "Plane@1");
		//map.put("title", "Mrs,");
		//map.put("birth_date", 26);
		//map.put("birth_month", 05);
		//map.put("birth_year", 1994);
		map.put("firstname", "Priyanka");
		map.put("lastname", "Mahesh");
		//map.put("company", "RR solutions");
		map.put("address1", "RR nagar1");
		//map.put("address2", "RR nagar1");
		map.put("country", "India");
		map.put("zipcode", 569093);
		map.put("state", "Karnataka");
		map.put("city", "Bangalore");
		map.put("mobile_number", 7755336892l);
		
		given()
		  . params(map)
		.when()
		   .post("https://automationexercise.com/api/createAccount")
		  .then()
		  .log().all();

	}
	@Test
	public void deleteMethodToDeleteUserAccount() {
		given()
	    .param("email", "priyanka213@gmail.com")
	    .param("password", "Plane@1")
    .when()
	    .delete("https://automationexercise.com/api/verifyLogin")
	.then()
	     .statusCode(200)
	     .log().all();
	}
	@Test
	public void updateUserAccount() {
		int r=new Random().nextInt();
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("name", "Priyanka");
		map.put("email", "priyanka213@gmail.com");
		map.put("password", "Plane@1");
		map.put("title", "mrs");
		map.put("birth_date", 26);
		map.put("birth_month", 05);
		map.put("birth_year", 1994);
		map.put("firstname", "Priyanka");
		map.put("lastname", "Mahesh");
		map.put("company", "RR solutions");
		map.put("address1", "RR nagar2");
		map.put("address2", "RR nagar3");
		map.put("country", "India");
		map.put("zipcode", 569093);
		map.put("state", "Karnataka");
		map.put("city", "Bangalore");
		map.put("mobile_number", 9643682789l);
		
		given()
		  .formParams(map)
		.when()
		   .put("https://automationexercise.com/api/updateAccount")
		 .then()
		     .statusCode(200)
		     .log().all();
	}
	@Test
	public void getUserAccount() {
		given()
		    .param("email", "priyanka213@gmail.com")
		.when()
		    .get("https://automationexercise.com/api/getUserDetailByEmail")
		.then()
		    .statusCode(200)
		    .log().all();
	}
	
}
