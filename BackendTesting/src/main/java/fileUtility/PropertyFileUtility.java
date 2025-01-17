package fileUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtility {

	public String getDataFromPropertyFile(String key) throws IOException {
		FileInputStream fis=new FileInputStream("./src/test/resources/commonData.properties");
		Properties pobj=new Properties();
		pobj.load(fis);
		String data=pobj.getProperty(key);
		return data;
	}

}