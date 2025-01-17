package EncodingAndDecoding;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdatePayrollInfoViaAESEncryption {

	@Test
	public void getAllPayrollTest() {
		String token="eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJsZUN3YUNPai00RXVlbzJkTFFrTmZuLXh2M0F3Nm5ZdWZtS0pINXBFWkNNIn0.eyJleHAiOjE3MzIxNzUyODgsImlhdCI6MTczMjE3Mzk1MSwianRpIjoiM2Y1NWVmNDgtZDMzZi00YzNhLTk2OWItZDdlODM2ZmNjNGY3IiwiaXNzIjoiaHR0cDovLzQ5LjI0OS4yOC4yMTg6ODE4MC9hdXRoL3JlYWxtcy9uaW56YSIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiI0ODVlYTJhYi1kY2MzLTQwOGItOTUxZi04NGMwM2JmZDAwMmYiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJuaW56YS1jbGllbnQiLCJzZXNzaW9uX3N0YXRlIjoiOTU0N2Y5NDMtYjZlMC00NDliLTgzY2QtYzcwYTkxNWZjOTU1IiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyJodHRwOi8vNDkuMjQ5LjI4LjIxODo4MDkxIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJkZWZhdWx0LXJvbGVzLW5pbnphIiwib2ZmbGluZV9hY2Nlc3MiLCJhZG1pbiIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJlbWFpbCBwcm9maWxlIiwic2lkIjoiOTU0N2Y5NDMtYjZlMC00NDliLTgzY2QtYzcwYTkxNWZjOTU1IiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJybWd5YW50cmEifQ.cEgqp9qnM9FZd6Buo-bpgLz_4a_FWjYAPaXnfr-oMGfx7czKYSSFfURfKQHBhsi4nb6UH5fHBFPuEp0-y_CrxynnY3sAXJ-LZyPOgNPHVssVOab46PAfEeQISTcKh4Vtw5-Hg78oIGJQjLO4zBE2H8u4FgZCZhoDBPGwaM2RDUujwH85x41WOQsX5hye8cbW5ZUXzcTPOpE9tychdpNZHwfy18v2yWLDLYVh0Bnu5C3FVAeOYY-ns6JYorH5vf7sPuN_0ZBuxYVo5cx_9zDgPW-PfEiRMD-i9N_fJYIO0kkhSmXZ5v4kFBg_IXJXOyfLKktxfeU44_Av5GItPQOToA";
		given()
		   .auth().oauth2(token)
		.when()
		    .get("http://49.249.28.218:8091/admin/payrolls")
		.then()
		    .log().all();
	}
	@Test
	public void updatePayrollTest() throws Exception {
//			String token="eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJsZUN3YUNPai00RXVlbzJkTFFrTmZuLXh2M0F3Nm5ZdWZtS0pINXBFWkNNIn0.eyJleHAiOjE3MzIxNzc2NjcsImlhdCI6MTczMjE3NTg5MSwianRpIjoiZGFiOTc5ZGMtNmFkNy00MDQ5LWJjNTEtODkwMmM4ODQ5NzZlIiwiaXNzIjoiaHR0cDovLzQ5LjI0OS4yOC4yMTg6ODE4MC9hdXRoL3JlYWxtcy9uaW56YSIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiI0ODVlYTJhYi1kY2MzLTQwOGItOTUxZi04NGMwM2JmZDAwMmYiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJuaW56YS1jbGllbnQiLCJzZXNzaW9uX3N0YXRlIjoiODE1MjUxODAtNzEwYi00YTVjLWIxY2MtY2ZmZTExNTliNTAxIiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyJodHRwOi8vNDkuMjQ5LjI4LjIxODo4MDkxIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJkZWZhdWx0LXJvbGVzLW5pbnphIiwib2ZmbGluZV9hY2Nlc3MiLCJhZG1pbiIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJlbWFpbCBwcm9maWxlIiwic2lkIjoiODE1MjUxODAtNzEwYi00YTVjLWIxY2MtY2ZmZTExNTliNTAxIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJybWd5YW50cmEifQ.ERbOpmbeIO1StIE6dd7qq5-KVkZfOkKSJn0JgKUjWjyBIm5ji76FjrorvUTawRKDmWdEWTlJxxOYpjgpFtfwIfXmlQgv0ugMgsfeoPfIUGbvwAGu_33F1p1F8RHLRWcF9CmjimG5D67PZNWtKX15UDl1IlB1xCq8CbwZHPPAzPSUuJWGQ2sJ0p5FA-1uUzeHfzDnOUXzJ-Vfii516hg3LQivJaExNrdcs_mtubdsWpSwZo8ELNbDhTlXV1kGJ4R9nYkEgLAmDdMibH_6EfTQUYP9V5VGrOqtcbH1is5wdE0YeAw_vQgCPoy4K0FEdDPTqopAB-063fh3ZKyk9NAv_A";
//			given()
//			.auth().oauth2(token) 
//			.when()
//			   .get("http://49.249.28.218:8091/admin/payrolls")
//			.then()
//			    .log().all();
		String jbody="{ \"employee\": { \"empId\":\"NH_01110\", \"designation\": \"string\", \"dob\": \"dd/MM/yyyy\", \"email\": \"string\", \"empName\": \"string\", \"experience\": 0, \"mobileNo\": \"string\", \"project\": \"string\", \"role\": \"string\", \"username\": “string” }, \"basicPlusVda\": 0, \"hra\": 4000, \"insurance\": 1000, \"lta\": 1000, \"lwf\": 1000, \"netPay\": 10000, \"payroll_id\": 1072, \"pf\": 0, \"pt\": 0, \"stat_bonus\": 0, \"status\": “Active” }";
		String privateKey="Ac03tEam@j!tu_#1";
		AESEncryptionAndDecryptionStandard edData=new AESEncryptionAndDecryptionStandard();
		String iReqBody=edData.encrypt(jbody,privateKey);
		System.out.println(iReqBody);
		
	   Response resp= given()
		     
	       .body(iReqBody)
	       .contentType(ContentType.JSON)
	    .when()
	       .put("http://49.249.28.218:8091/payroll");
	    resp.then()
	        .log().all();
	     String respBody=  edData.decrypt(resp.getBody().asString(),privateKey);
	     System.out.println(respBody);
	       
	}
}
