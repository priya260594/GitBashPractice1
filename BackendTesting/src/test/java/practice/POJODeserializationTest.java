package practice;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class POJODeserializationTest {

	public static void main(String[] args) throws StreamReadException, DatabindException, IOException  {
		ObjectMapper obj=new ObjectMapper();
		Project pobj=obj.readValue(new File("./Sample.json"), Project.class);
		System.out.println(pobj.getCreatedBy());
		System.out.println(pobj.getProjectName());
		System.out.println(pobj.getStatus());
		System.out.println(pobj.getTeamSize());
		System.out.println(pobj.getClass());
	
	}  
}
