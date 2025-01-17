package Assignment;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Run2_ProjectWithArrayAndObject {

	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
     ObjectMapper obj=new ObjectMapper();
     Run1_ProjectWithArrayAndObject pm=obj.readValue(new File("./ProjectWithAll.json"), Run1_ProjectWithArrayAndObject.class);
     System.out.println(pm.getPmobj().get(0).getName());
     System.out.println(pm.getPmobj().get(0).getId());
     System.out.println(pm.getPmobj().get(1).getName());
     System.out.println(pm.getPmobj().get(1).getId());

	}

}
