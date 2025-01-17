package Assignment;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

class EmployeeWithArr{
	private String empName;
	private String empId;
	private int salary;
	private long[] phNo;
	
	public EmployeeWithArr() {}

	public EmployeeWithArr(String empName, String empId, int salary, long[] phNo) {
		super();
		this.empName = empName;
		this.empId = empId;
		this.salary = salary;
		this.phNo = phNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public long[] getPhNo() {
		return phNo;
	}

	public void setPhNo(long[] phNo) {
		this.phNo = phNo;
	}
	
	
}
public class EmployeeWithArray {

	public static void main(String[] args) throws StreamWriteException, DatabindException, IOException {
		long[] phnos= {7789034653l,7899224564l};
		EmployeeWithArr empArr=new EmployeeWithArr("Shanthi", "RR_77", 40000, phnos);
		ObjectMapper obj=new ObjectMapper();
		obj.writeValue(new File("./EmployeeWithArray.json"), empArr);
		System.out.println("Serialization done");
	
	}

}
