package Assignment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

class ProjectManager {
	private String name;
	private String id;

	public ProjectManager() {
	}

	public ProjectManager(String name, String id) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}

public class Run1_ProjectWithArrayAndObject {
	private String projectName;
	private String projectStatus;
	private List<String> teamMembers;
	private List<ProjectManager> pmobj;

	public Run1_ProjectWithArrayAndObject() {
	}

	public Run1_ProjectWithArrayAndObject(String projectName, String projectStatus, List<String> teamMembers,
			List<ProjectManager> pmobj) {
		super();
		this.projectName = projectName;
		this.projectStatus = projectStatus;
		this.teamMembers = teamMembers;
		this.pmobj = pmobj;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	public List<String> getTeamMembers() {
		return teamMembers;
	}

	public void setTeamMembers(List<String> teamMembers) {
		this.teamMembers = teamMembers;
	}

	public List<ProjectManager> getPmobj() {
		return pmobj;
	}

	public void setPmobj(List<ProjectManager> pmobj) {
		this.pmobj = pmobj;
	}

	public static void main(String[] args) throws StreamWriteException, DatabindException, IOException {
		List<String> team= new ArrayList<String>();
		team.add("John");
		team.add("priya");
		team.add("Devi");
		
		ProjectManager pm1=new ProjectManager("Reichal", "TP_01");
		ProjectManager pm2=new ProjectManager("Ravi", "TP_02");
		List<ProjectManager> pm=new ArrayList<ProjectManager>();
		pm.add(pm1);
		pm.add(pm2);
		
		Run1_ProjectWithArrayAndObject pobj=new Run1_ProjectWithArrayAndObject("HRM_Orange", "Created", team, pm);
		ObjectMapper obj=new ObjectMapper();
		obj.writerWithDefaultPrettyPrinter().writeValue(new File("./ProjectWithAll.json"), pobj);
		System.out.println("Serialization done");
		

	}

}

