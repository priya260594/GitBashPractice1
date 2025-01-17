package Miscellaneous;

import java.util.Random;
import static io.restassured.RestAssured.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(value= {"projectName","teamSize","createdBy","status"})
@JsonIgnoreProperties(value={"status","projectName"})
class Project{
	@JsonProperty(value=("crted  BY"))
	private String createdBy;
	private String projectName;
	private String status;
	private int teamSize;
	
	public Project() {}
	public Project(String createdBy, String projectName, String status, int teamSize) {
		this.createdBy=createdBy;
		this.projectName=projectName;
		this.status=status;
		this.teamSize=teamSize;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getTeamSize() {
		return teamSize;
	}
	public void setTeamSize(int teamSize) {
		this.teamSize = teamSize;
	}
}

public class DataMasking {

	public static void main(String[] args) {
		Random r=new Random();
		int ran=r.nextInt(1000);
		Project pobj=new Project("Priya","HRM_"+ran,"Ongoing",0);
		
		

	}

}
