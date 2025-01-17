package practice;

import static com.backent.testing.ClassA.given;
import static com.backent.testing.ClassA.then;
import static com.backent.testing.ClassA.when;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SampleTest {
	@Test
	public void sampleRestAssureTest() {
		Response res=RestAssured.get("http://49.249.28.218:8091/project/NH_PROJ_5553");
       // System.out.println(res.prettyPeek()); //entire response details in readable format--1
		res.then().assertThat().statusCode(201);
		res.then().log().all(); //entire response details in readable format--2
       
	}
	
	@Test
	public void sampleStaticImportTest() {
		given();
		when();
		then();
	}

	
}
