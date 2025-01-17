package HMS_POJOClass;

public class PatientRegister {
	private String fullName;
	private String Address;
	private String City;
	private String gender;
	private String email;
	private String password;

	public PatientRegister() {
	}

	public PatientRegister(String fullName, String address, String city, String gender, String email,
			String password) {
		super();
		this.fullName = fullName;
		Address = address;
		City = city;
		this.gender = gender;
		this.email = email;
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
