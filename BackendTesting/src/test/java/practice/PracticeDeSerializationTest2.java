package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class PracticeDeSerializationTest2 {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		FileInputStream fileInput=new FileInputStream("./SerializeFile.text");
		ObjectInputStream objInp=new ObjectInputStream(fileInput);
		NFSGame userObj=(NFSGame)objInp.readObject();
		System.out.println(userObj.level);
		System.out.println(userObj.life);
	}
}
