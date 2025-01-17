package HMS_POJOClass;

public class BookAppointment {
	private String doctorSpec;
	private String doctor;
	private String data;
	private String time;

	public BookAppointment() {

	}

	public BookAppointment(String doctorSpec, String doctor, String data, String time) {
		super();
		this.doctorSpec = doctorSpec;
		this.doctor = doctor;
		this.data = data;
		this.time = time;
	}

	public String getDoctorSpec() {
		return doctorSpec;
	}

	public void setDoctorSpec(String doctorSpec) {
		this.doctorSpec = doctorSpec;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
