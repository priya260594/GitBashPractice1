package Assignment;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

class EmployeeWithSpouse {
	private String EmployeeName;
	private String EmployeeId;
	private long[] phno;
	SpouseDetail spouse;

	public EmployeeWithSpouse() {
	}

	public EmployeeWithSpouse(String employeeName, String employeeId, long[] phno, SpouseDetail spouse) {
		super();
		EmployeeName = employeeName;
		EmployeeId = employeeId;
		this.phno = phno;
		this.spouse = spouse;
	}

	public String getEmployeeName() {
		return EmployeeName;
	}

	public void setEmployeeName(String employeeName) {
		EmployeeName = employeeName;
	}

	public String getEmployeeId() {
		return EmployeeId;
	}

	public void setEmployeeId(String employeeId) {
		EmployeeId = employeeId;
	}

	public long[] getPhno() {
		return phno;
	}

	public void setPhno(long[] phno) {
		this.phno = phno;
	}

	public SpouseDetail getSpouse() {
		return spouse;
	}

	public void setSpouse(SpouseDetail spouse) {
		this.spouse = spouse;
	}

}

public class EmployeeWithObject {

	public static void main(String[] args) throws StreamWriteException, DatabindException, IOException {
		long[] phnos= {4534535353l,562624353543l};
		SpouseDetail sp=new SpouseDetail("Priya", 752, 57567463666l);
	    EmployeeWithSpouse empSp=new EmployeeWithSpouse("Mahesh", "TCS_004", phnos,sp);
		ObjectMapper obj=new ObjectMapper();
		obj.writerWithDefaultPrettyPrinter().writeValue(new File("./EmployeeWithObject.json"), empSp);
		System.out.println("serialization done");
		
	}

}
