package mockingSolution;

import org.mockito.Mockito;

class PanCard {
	public String isValid(String pancard) {
		if (pancard.matches("[A-Z]{5}[0-9]{4}[A-Z]") == true) {
			return "pancard is valid";
		} else {
			return "pancard is invalid";
		}
	}
	public static PanCard getObject() {
		PanCard mocObj=Mockito.mock(PanCard.class);
		Mockito.when(mocObj.isValid("ABCDE1234A")).thenReturn("pancard is valid");
		Mockito.when(mocObj.isValid("ABCDE1234B")).thenReturn("Pancard is valid");
		Mockito.when(mocObj.isValid("ABCDE1234C")).thenReturn("pancard is not valid");
		return mocObj;
	}
}

public class MockingTest {

	public static void main(String [] args) {
		//System.out.println(PanCard.isValid("AEaRW1257T"));
		PanCard obj=PanCard.getObject();
		System.out.println(obj.isValid("ABCDE1234D")); 
	}
}
