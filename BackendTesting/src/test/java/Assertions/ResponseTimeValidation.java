package Assertions;

import static io.restassured.RestAssured.when;

import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class ResponseTimeValidation {
	@Test
	public void responseTimeValidation() {
		Response response=when()
				   .get("http://49.249.28.218:8091/project/NH_PROJ_6245");
		long timeTaken=response.time();
		System.out.println("The response time is "+timeTaken); //time will be in long only
		System.out.println("Response time (in seconds) "+response.timeIn(TimeUnit.SECONDS));
		response.then().assertThat().time(Matchers.lessThan(1000l));
		response.then().log().all();
		
	}
}
