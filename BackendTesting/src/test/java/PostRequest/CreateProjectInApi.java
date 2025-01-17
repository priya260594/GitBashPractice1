package PostRequest;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;

import PojoUtility.UsingPOJOClass;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class CreateProjectInApi {

	@Test
	public void CreateProjectInApi() throws SQLException, InterruptedException {
		Random ran = new Random();
		String projectName="HRM_"+ran.nextInt(1000);
		UsingPOJOClass obj = new UsingPOJOClass("Priya", projectName  , "Ongoing", 0);

		Response res=given().body(obj).contentType(ContentType.JSON).when().post("http://49.249.28.218:8091/addProject");

		String projectId=res.jsonPath().get("projectId");
				res.then().body(JsonSchemaValidator.matchesJsonSchema(new File("./src/test/resources/jsonSchema.json")))
				.assertThat().statusCode(201).log().all();
				
				Driver driverRef=new Driver();
				DriverManager.registerDriver(driverRef);
				Connection con=DriverManager.getConnection("jdbc:mysql://49.249.28.218:3333/ninza_hrm","root","root");
				System.out.println("connection done");
				ResultSet result=con.createStatement().executeQuery("select * from project");
				while(result.next()) {
					System.out.println(result.getString(4));			}
			    
					con.close();
					System.out.println("Database closed");
				}

	}

