package ShopperStack;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ShopperScenarioTest {
	String token,zoneId;
	int shopperId,productId,itemId,addressId=65226,orderId=33484;//itemId=237610,productId=23
	@Test
	public void shopperLogin() {
		String reqBody="{\r\n"
				+ "  \"email\": \"priyankaboopu@gmail.com\",\r\n"
				+ "  \"password\": \"Plane@100\",\r\n"
				+ "  \"role\": \"SHOPPER\"\r\n"
				+ "}";
		Response res=given()
			  .body(reqBody)
			  .contentType(ContentType.JSON)
		.when()
		      .post("https://www.shoppersstack.com/shopping/users/login");
		res.then()
		      .statusCode(200)
		      .log().all();
		token=res.jsonPath().get("data.jwtToken");
		zoneId=res.jsonPath().get("data.zoneId");
		shopperId=res.jsonPath().get("data.userId");
		System.out.println(token);
		System.out.println("=================shopper got login===========================");
	}
	@Test(dependsOnMethods = "shopperLogin")
	public void getDefaultProductTest() {
		Response res2=given()
		   .auth().oauth2(token)
		   .queryParam("zoneId", zoneId)
		.when()
		    .get("https://www.shoppersstack.com/shopping/products");
		res2.then()
	      .statusCode(200)
	      .log().all();
	    productId=res2.jsonPath().get("data[7].productId");
		System.out.println(productId);
		System.out.println(shopperId);
		System.out.println("====================Getting all Products===================");
     }
	
	@Test(dependsOnMethods = {"shopperLogin","getDefaultProductTest"})
	public void addProductToWishList(){
		String body="{\r\n"
				+ "  \"productId\": "+productId+",\r\n"
				+ "  \"quantity\": 1\r\n"
				+ "}";
		Response res2=given()
				   .auth().oauth2(token)
				   .body(body)
		           .contentType(ContentType.JSON)
				   .pathParam("shopperId", shopperId)
				.when()
  				    .post("https://www.shoppersstack.com/shopping/shoppers/{shopperId}/wishlist");
				res2.then()
			      .statusCode(201)
			      .log().all();
				System.out.println("====================Product added to wishlist===================");
	}
	@Test(dependsOnMethods = {"shopperLogin","addProductToWishList","getDefaultProductTest"})
	public void getProductFromWishList() {
		given()
		     .auth().oauth2(token)
		     .pathParam("shopperId", shopperId)
		.when()
		      .get("https://www.shoppersstack.com/shopping/shoppers/{shopperId}/wishlist")
		.then()
		.statusCode(200)
		.log().all();
		System.out.println("=====================getProduct from wishlist=======================");
	}
	
	@Test(dependsOnMethods = {"shopperLogin","getDefaultProductTest","addProductToWishList"})
	public void addProductToCart() {
		String reqBody="{\r\n"
				+ "  \"productId\": "+productId+",\r\n"
				+ "  \"quantity\": 1\r\n"
				+ "}";
		Response res=given()
		    .auth().oauth2(token)	
		    .body(reqBody)
		    .contentType(ContentType.JSON) 
		    .pathParam("shopperID", shopperId)
		.when()  
		     .post("https://www.shoppersstack.com/shopping/shoppers/{shopperID}/carts");
		res.then()
		     .statusCode(201)
		     .log().all();
		itemId=res.jsonPath().getInt("data.itemId");
		System.out.println(itemId);
		System.out.println("=========================Product added to cart=========================");
	}
	@Test(dependsOnMethods = {"shopperLogin"})
	public void orderProduct() {
		String reqBody="{\r\n"
				+ "  \"address\": {\r\n"
				+ "    \"addressId\": "+addressId+",\r\n"
				+ "    \"buildingInfo\": \"Om shakthi illam\",\r\n"
				+ "    \"city\": \"Bengaluru\",\r\n"
				+ "    \"country\": \"India\",\r\n"
				+ "    \"landmark\": \"behind Anna kutteera\",\r\n"
				+ "    \"name\": \"Shanthi\",\r\n"
				+ "    \"phone\": \"9867656783\",\r\n"
				+ "    \"pincode\": \"560010\",\r\n"
				+ "    \"state\": \"Karnataka\",\r\n"
				+ "    \"streetInfo\": \"Ambethkar\",\r\n"
				+ "    \"type\": \"Home\"\r\n"
				+ "  },\r\n"
				+ "  \"paymentMode\": \"COD\"\r\n"
				+ "}";
		Response res=given()
				             .auth().oauth2(token)
				             .body(reqBody)
				             .contentType(ContentType.JSON)
				             .pathParam("shopperID", shopperId)
				          .when()
				             .post("https://www.shoppersstack.com/shopping/shoppers/{shopperID}/orders");
		res.then().log().all();
		orderId=res.jsonPath().getInt ("data.orderId");
		System.out.println("The order id is "+orderId);
		System.out.println(orderId);
		System.out.println("=========================Product got ordered=========================");
		
	}
	@Test(dependsOnMethods = "shopperLogin")
	public void updateOrderStatus() {
		given()
		     .auth().oauth2(token)
		     .pathParam("shopperID", shopperId)
		     .pathParam("orderID", orderId)
		     .queryParam("status", "DELIVERED")
		.when()
		     .patch("https://www.shoppersstack.com/shopping/shoppers/{shopperID}/orders/{orderID}")
		.then()
		     .statusCode(200)
		     .log().all();	     
		System.out.println("=========================Product status updated=========================");
	}
	@Test(dependsOnMethods = {"shopperLogin","getDefaultProductTest"})
	public void reviewProduct() {
		String reviewProductBody="{\r\n"
				+ "  \"description\": \"This is a nice product\",\r\n"
				+ "  \"heading\": \"Good\",\r\n"
				+ "  \"rating\": 4.0,\r\n"
				+ "  \"shopperId\": "+shopperId+",\r\n"
				+ "  \"shopperName\": \"PRI\"\r\n"
				+ "}";
		given()
		    .auth().oauth2(token)
		    .body(reviewProductBody)
		    .contentType(ContentType.JSON)
		    .queryParam("productId", productId)
		.when()  
		    .post("https://www.shoppersstack.com/shopping/reviews")	
		.then()
		    .statusCode(200)
		    .log().all();
		System.out.println("=========================Review the ordered product=========================");
	}
	@Test(dependsOnMethods = {"shopperLogin","getDefaultProductTest"})
	public void deleteProductFromWishList() {
		Response res2=given()
				   .auth().oauth2(token)
				   .pathParam("shopperID", shopperId)
				   .pathParam("productID", productId)
				.when()
				    .delete("https://www.shoppersstack.com/shopping/shoppers/{shopperID}/wishlist/{productID}");
				res2.then()
			      .statusCode(204)
			      .log().all();
				System.out.println("=========================deleting product from wishlist=========================");
	}
	@Test(dependsOnMethods = {"shopperLogin","getDefaultProductTest"})
	public void deleteProductToCart() {
		String reqBody="{\r\n"
				+ "  \"productId\": "+productId+",\r\n"
				+ "  \"quantity\": 1\r\n"
				+ "}";
		Response res=given()
		    .auth().oauth2(token)	
		    .body(reqBody)
		    .contentType(ContentType.JSON) 
		    .pathParam("shopperID", shopperId)
		    .pathParam("productID", productId)
		.when()  
		     .delete("https://www.shoppersstack.com/shopping/shoppers/{shopperID}/carts/{productID}");
		res.then()
		     .statusCode(200)
		     .log().all();
		itemId=res.jsonPath().getInt("data.itemId");
		System.out.println(itemId);
		System.out.println("=========================Product deleted from the cart=========================");
	}
	@Test(dependsOnMethods = "shopperLogin")
	public void addNewAddressTest() {
		String reqBody="{\r\n"
				+ "  \"buildingInfo\": \"Om shakthi illam\",\r\n"
				+ "  \"city\": \"Bengaluru\",\r\n"
				+ "  \"country\": \"India\",\r\n"
				+ "  \"landmark\": \"behind Anna kuteera\",\r\n"
				+ "  \"name\": \"Boopathy\",\r\n"
				+ "  \"phone\": \"9867656783\",\r\n"
				+ "  \"pincode\": \"560010\",\r\n"
				+ "  \"state\": \"Karnataka\",\r\n"
				+ "  \"streetInfo\": \"string\",\r\n"
				+ "  \"type\": \"Home\"\r\n"
				+ "}";
		Response res=given()
				        .auth().oauth2(token)
				        .body(reqBody)
				        .contentType(ContentType.JSON)
				        .pathParam("shopperID", shopperId)
				    .when()
				         .post("https://www.shoppersstack.com/shopping/shoppers/{shopperID}/address");
		res.then()
		        .statusCode(201)
		        .log().all();
		addressId=res.jsonPath().getInt("data.addressId");
		System.out.println("The address id is "+addressId); //65226
		System.out.println("=======================adding the new address=====================");
	}
	@Test(dependsOnMethods = "shopperLogin")
	public void deleteAddressTest() {
		given()
		      .auth().oauth2(token)
		      .pathParam("addressID", addressId)
		      .pathParam("shopperID", shopperId)
		 .when()
		      .delete("https://www.shoppersstack.com/shopping//shoppers/{shopperID}/address/{addressID}")
		 .then()
		      .statusCode(204)
		      .log().all();
		System.out.println("=======================address successfully deleted=====================");
	}
	
}
