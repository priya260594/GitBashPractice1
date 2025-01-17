package EncodingAndDecoding;

import static io.restassured.RestAssured.given;

import java.util.Base64;

import org.testng.annotations.Test;

public class Base64EncodeAndDecode {

	@Test
	public void sampleEncodeAndDecode() {
		String encodedData=new String(Base64.getEncoder().encode("rmgyantra:rmgy@9999".getBytes()));
		System.out.println("EncodedData is "+encodedData);
		
		String decodedData=new String(Base64.getDecoder().decode("cm1neWFudHJhOnJtZ3lAOTk5OQ==".getBytes()));
		System.out.println("DecodedData is "+decodedData);
	}
	
	@Test
	public void EncodeApiTest() {
		given()
		    .auth().preemptive().basic("rmgyantra", "rmgy@9999")
		    .log().all()
		.when()
		    .get("http://49.249.28.218:8091/project/NH_PROJ_6581")
		.then()
		     .log().all();
		    // .statusCode(200);/NH_PROJ_6581
	}
}
