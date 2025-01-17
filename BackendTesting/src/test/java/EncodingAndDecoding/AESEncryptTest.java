package EncodingAndDecoding;

import org.testng.annotations.Test;

public class AESEncryptTest {

	@Test
	public void AESEncryptTest() throws Exception {
		String privateKey="Ac03tEam@j!tu_#1";
		String data="{\"name\":\"Priyanka\",\"id\":\"TP_07\"}";
		
		AESEncryptionAndDecryptionStandard edData=new AESEncryptionAndDecryptionStandard();
		System.out.println(edData.encrypt(data,privateKey));
		
		System.out.println(edData.decrypt("qu5QruQSxaDvUzEeSyeC27SDe+UBofsd2aCLiA9IbokX6De4MOCGN3Ka/GW15ojD", privateKey));
		
	}
}
