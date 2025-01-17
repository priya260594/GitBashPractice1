package Assignment;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EmployeeWithObject2 {

	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
		ObjectMapper obj=new ObjectMapper();
		EmployeeWithSpouse empSp=obj.readValue(new File("./EmployeeWithObject.json"), EmployeeWithSpouse.class);
		System.out.println(empSp.spouse.getSp_Name());
		System.out.println(empSp.spouse.getSp_Id());
		System.out.println(empSp.spouse.getSp_phNo());
		System.out.println(Arrays.toString(empSp.getPhno()));
		System.out.println(empSp.getEmployeeId());
		System.out.println(empSp.getEmployeeName());
	}

}
