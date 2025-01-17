package Miscellaneous;

import static io.restassured.RestAssured.given;

public class CookiesTest {
    public static void main(String[] args) {
    	given().cookie("username","Priyanka").log().all()
    	.when().get("http://49.249.28.218:8091/project/NH_PROJ_5944")
		.then().log().all();
       }
}
