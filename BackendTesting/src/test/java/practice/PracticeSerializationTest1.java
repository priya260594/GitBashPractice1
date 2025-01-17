package practice;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class NFSGame implements Serializable{
	String userName;
	int level;
	long score;
	int life;
	
	public NFSGame(String userName, int level, long score, int life){
		this.userName=userName;
		this.level=level;
		this.score=score;
		this.life=life;
	}
	
}
public class PracticeSerializationTest1 {

	public static void main(String[] args) throws IOException {
		NFSGame userObj=new NFSGame("Priya", 16, 78990, 5);
		
		FileOutputStream fos=new FileOutputStream("./SerializeFile.text");
		ObjectOutputStream objOut=new ObjectOutputStream(fos);
		objOut.writeObject(userObj);
		System.out.println("======Serialization Done========");

	}

}
