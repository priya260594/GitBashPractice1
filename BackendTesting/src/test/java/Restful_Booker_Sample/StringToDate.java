package Restful_Booker_Sample;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class StringToDate {
	    public static void main(String[] args) throws ParseException {
	        String dateString ="2020-11-12";
//	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            LocalDate localDate = LocalDate.parse(dateString, formatter); 
//            System.out.println(localDate);
//            String formattedDate = localDate.format(formatter);
//            System.out.println("Parsed Date: " + formattedDate);
	        Date dobj=new Date();
	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	        sdf.format(dateString);
	       dobj=sdf.parse(dateString);
	        
	    	// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    	 //LocalDate localDate = LocalDate.parse(dateString, formatter);
	    	//System.out.println(localDate);
            // Convert LocalDate to Date
           // Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            System.out.println("Date: " + dobj);
	    }
}
