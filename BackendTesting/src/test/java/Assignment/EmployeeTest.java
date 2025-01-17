package Assignment;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

class Employee{
	private String empName;
	private String empId;
	private int salary;
	private long phNo;
	
	public Employee() {} 
	
	public Employee(String empName, String empId, int salary, long phNo) {
		this.empName=empName;
		this.empId=empId;
		this.salary=salary;
		this.phNo=phNo;
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

	public long getPhNo() {
		return phNo;
	}

	public void setPhNo(long phNo) {
		this.phNo = phNo;
	}
	}

public class EmployeeTest {

	public static void main(String[] args) throws StreamWriteException, DatabindException, IOException {
		Employee emp=new Employee("Priya","HR_002",40000,8543278904l);
		ObjectMapper obj=new ObjectMapper();
		obj.writeValue(new File("./Employee.json"), emp);
		System.out.println("Employee serialization done");
	
	}

}
