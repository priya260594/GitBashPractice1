package fileUtility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JavaUtility {
	public Date getSystemDateYYYYDDMMDate(String dateStr) throws ParseException
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dobj=formatter.parse(dateStr);
		return dobj; 

		
//		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern();
//         LocalDate localDate = LocalDate.parse(dateStr, formatter); 
//         Date formattedDate = (Date)localDate.format(formatter);
//         return formattedDate;
	}
}
