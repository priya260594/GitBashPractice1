package ShopperStack;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ShopperStackAllInOneTest {
	@Test
	public void orderProductAndGiveReview() {
		String token,zoneId;
		int shopperId,productId,itemId,addressId,orderId;
		/*********************************Shopper login***************************************/
		String shopperLoginBody="{\r\n"
				+ "  \"email\": \"priyankaboopu@gmail.com\",\r\n"
				+ "  \"password\": \"Plane@100\",\r\n"
				+ "  \"role\": \"SHOPPER\"\r\n"
				+ "}";
		Response loginRes=given()
			  .body(shopperLoginBody)
			  .contentType(ContentType.JSON)
		.when()
		      .post("https://www.shoppersstack.com/shopping/users/login");
		loginRes.then()
		      .statusCode(200)
		      .log().all();
		token=loginRes.jsonPath().get("data.jwtToken");
		zoneId=loginRes.jsonPath().get("data.zoneId");
		shopperId=loginRes.jsonPath().get("data.userId");
		System.out.println(token);
		System.out.println("=================shopper got login===========================");
		/*****************************Get All default products*******************************************/
		Response getAllProductsRes=given()
				   .auth().oauth2(token)
				   .queryParam("zoneId", zoneId)
				.when()
				    .get("https://www.shoppersstack.com/shopping/products");
		getAllProductsRes.then()
			      .statusCode(200)
			      .log().all();
	    productId=getAllProductsRes.jsonPath().get("data[7].productId");
		System.out.println(productId);
		System.out.println(shopperId);
		System.out.println("====================Getting all Products===================");
		/************************************Add Product To Wishlist*****************************************/		
		String addToWishlistBody="{\r\n"
						+ "  \"productId\": "+productId+",\r\n"
						+ "  \"quantity\": 1\r\n"
						+ "}";
		Response addToWishlistRes=given()
						   .auth().oauth2(token)
						   .body(addToWishlistBody)
				           .contentType(ContentType.JSON)
						   .pathParam("shopperId", shopperId)
						.when()
		  				    .post("https://www.shoppersstack.com/shopping/shoppers/{shopperId}/wishlist");
		   addToWishlistRes.then()
					      .statusCode(201)
					      .log().all();
		System.out.println("====================Product added to wishlist===================");
		/*************************************Add Product To Cart***************************************/
		String addToCartBody="{\r\n"
								+ "  \"productId\": "+productId+",\r\n"
								+ "  \"quantity\": 1\r\n"
								+ "}";
	    Response addToCartRes=given()
						    .auth().oauth2(token)	
						    .body(addToCartBody)
						    .contentType(ContentType.JSON) 
						    .pathParam("shopperID", shopperId)
						.when()  
						     .post("https://www.shoppersstack.com/shopping/shoppers/{shopperID}/carts");
	    addToCartRes.then()
						     .statusCode(201)
						     .log().all();
		itemId=addToCartRes.jsonPath().getInt("data.itemId");
		System.out.println(itemId);
		System.out.println("=========================Product added to cart=========================");
		/*****************************************Add new address**********************************/
		String addNewAddressBody="{\r\n"
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
		Response addNewAddressRes=given()
				        .auth().oauth2(token)
				        .body(addNewAddressBody)
				        .contentType(ContentType.JSON)
				        .pathParam("shopperID", shopperId)
				    .when()
				         .post("https://www.shoppersstack.com/shopping/shoppers/{shopperID}/address");
		addNewAddressRes.then()
		        .statusCode(201)
		        .log().all();
		addressId=addNewAddressRes.jsonPath().getInt("data.addressId");
		System.out.println("The address id is "+addressId); //65226
		System.out.println("=======================adding the new address=====================");
		/***********************************Order cart Product***************************************/
		String orderProductBody="{\r\n"
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
		Response orderProductRes=given()
				             .auth().oauth2(token)
				             .body(orderProductBody)
				             .contentType(ContentType.JSON)
				             .pathParam("shopperID", shopperId)
				          .when()
				             .post("https://www.shoppersstack.com/shopping/shoppers/{shopperID}/orders");
		orderProductRes.then().log().all();
		orderId=orderProductRes.jsonPath().getInt ("data.orderId");
		System.out.println("The order id is "+orderId);
		System.out.println(orderId);
		System.out.println("=========================Product got ordered=========================");
		/********************************Update order status=Delivered**********************************/
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
		/*******************************Review the same product************************************/
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
		/**********************************Deleting the address*************************************/
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
	    /************************************Deleting the product from wishList************************/
	        given()
			   .auth().oauth2(token)
			   .pathParam("shopperID", shopperId)
			   .pathParam("productID", productId)
			.when()
			    .delete("https://www.shoppersstack.com/shopping/shoppers/{shopperID}/wishlist/{productID}")
	       .then()
		      .statusCode(204)
		      .log().all();
			System.out.println("=========================deleting product from wishlist=========================");
	}

}
