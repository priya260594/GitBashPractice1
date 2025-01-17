package Assignment;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EmployeeDeserialisationTest {

	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
	 ObjectMapper obj=new ObjectMapper();
	 Employee emp=obj.readValue(new File("./Employee.json"), Employee.class);
	 System.out.println(emp.getEmpName());
	 System.out.println(emp.getEmpId());
	 System.out.println(emp.getPhNo());
	 System.out.println(emp.getSalary());
	}

}
