  package PostRequest;

import org.json.simple.JSONObject;

public class ComplexRequestUsingJsonObject {
	


	    public static void main(String[] args) {
	        // Create a nested JSON structure
	        JSONObject address = new JSONObject();
	        address.put("street", "123 Main St");
	        address.put("city", "Springfield");
	        address.put("zipcode", "12345");

	        JSONObject user = new JSONObject();
	        user.put("name", "John Doe");
	        user.put("age", 30);
	        user.put("address", address);
	        user.put("hobbies", new String[]{"Reading", "Traveling", "Gaming"});
	        
	        System.out.println(user.toJSONString());

	        // Send POST request with JSON body
//	        RestAssured.given()
//	            .baseUri("https://example.com/api/users")
//	            .header("Content-Type", "application/json")
//	            .body(user.toString()) // Pass JSON object as a string
//	        .when()
//	            .post()
//	        .then()
//	            .statusCode(201) // Assuming the response should be 201 Created
//	            .log().all();
	    }
	}


