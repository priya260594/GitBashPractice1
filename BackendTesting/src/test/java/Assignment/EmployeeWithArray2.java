package Assignment;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EmployeeWithArray2 {
	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
	ObjectMapper obj=new ObjectMapper();
	EmployeeWithArr empArr=obj.readValue(new File("./EmployeeWithArray.json"), EmployeeWithArr.class);
	System.out.println(Arrays.toString(empArr.getPhNo()));
	System.out.println(empArr.getEmpId());
	System.out.println(empArr.getEmpName());
	System.out.println(empArr.getSalary());
	}
}
