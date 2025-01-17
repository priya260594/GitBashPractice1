package Mock;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.annotations.Test;

public class SampleTest {
	@Test
	public void loginWithvalidDetails() {
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("name", "Priyanka");
		map.put("email", "priyanka563@gmail.com");
		map.put("password", "Plane@1");
		map.put("title", "Mrs,");
		map.put("birth_date", 26);
		map.put("birth_month", 05);
		map.put("birth_year", 1994);
		map.put("firstname", "Priyanka");
		map.put("lastname", "Mahesh");
		map.put("company", "RR solutions");
		map.put("address1", "RR nagar1");
		map.put("address2", "RR nagar1");
		map.put("country", "India");
		map.put("zipcode", 569093);
		map.put("state", "Karnataka");
		map.put("city", "Bangalore");
		map.put("mobile_number", 7755336892l);
		
//		String obj="{\r\n"
//				+ "\"name\":\"devikalams\",\r\n"
//				+ "\"email\":\"devisaride@gmail.com\",\r\n"
//				+ "\"password\":\"Devivarsha@1\",\r\n"
//				+ "\"title\":\"Mrs\",\r\n"
//				+ "\"birth_date\":24,\r\n"
//				+ "\"birth_month\":06,\r\n"
//				+ "\"birth_year\":1998,\r\n"
//				+ "\"firstname\":\"devi\",\r\n"
//				+ "\"lastname\":\"kalam\",\r\n"
//				+ "\"company\":\"tekp\",\r\n"
//				+ "\"address1\":\"mahadevapura\",\r\n"
//				+ "\"address2\":\"krpuraam\",\r\n"
//				+ "\"country\":\"India\",\r\n"
//				+ "\"zipcode\":600534,\r\n"
//				+ "\"state\":\"Karnataka\",\r\n"
//				+ "\"city\":\"bangalore\",\r\n"
//				+ "\"mobile_number\":9876543210\r\n"
//				+ "}";
		given()
		  .formParams(map)
		.when()
		   .post("https://automationexercise.com/api/createAccount")
		  .then().log().all();

}
}
