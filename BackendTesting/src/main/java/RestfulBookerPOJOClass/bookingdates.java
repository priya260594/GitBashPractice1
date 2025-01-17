package RestfulBookerPOJOClass;

public class bookingdates{
	
	private String checkin;
	private String checkout;
	
	public bookingdates() {
		
	}
	public bookingdates(String checkin,String checkout) {
		this.checkin=checkin;
		this.checkout=checkout;
	}
	public String getCheckin() {
		return checkin;
	}
	public String getCheckout() {
		return checkout;
	}
	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}
	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}
	

}
