package Restful_Booker_Sample;

import org.testng.annotations.Test;

public class DataTypesConversion {
	@Test
	public void primitiveToString() {
		int num=123;
		String st=String.valueOf(num);
		System.out.println(st);
		String st2=num+"";
	}
	@Test
	public void StringToPrimitive() {
		String st="466";
		int n=Integer.parseInt(st);
		double d=Double.parseDouble(st);
		System.out.println(d);
	}

}
