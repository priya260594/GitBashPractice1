package HMS_POJOClass;

public class SubmitQuery {
	private String name;
	private String email;
	private String mobileNo;
	private String description;
	
	public SubmitQuery() {
		
	}
    public SubmitQuery(String name, String email, String mobileNo, String description) {
		super();
		this.name = name;
		this.email = email;
		this.mobileNo = mobileNo;
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	}
