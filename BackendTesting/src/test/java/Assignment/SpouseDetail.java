package Assignment;

public class SpouseDetail {
	private String sp_Name;
	private int sp_Id;
	private long sp_phNo;
	public SpouseDetail() {}
	public SpouseDetail(String sp_Name, int sp_Id, long sp_phNo) {
		super();
		this.sp_Name = sp_Name;
		this.sp_Id = sp_Id;
		this.sp_phNo = sp_phNo;
	}
	public String getSp_Name() {
		return sp_Name;
	}
	public void setSp_Name(String sp_Name) {
		this.sp_Name = sp_Name;
	}
	public int getSp_Id() {
		return sp_Id;
	}
	public void setSp_Id(int sp_Id) {
		this.sp_Id = sp_Id;
	}
	public long getSp_phNo() {
		return sp_phNo;
	}
	public void setSp_phNo(long sp_phNo) {
		this.sp_phNo = sp_phNo;
	}
	
}

