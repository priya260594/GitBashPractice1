package practice;

import static practice.MethodChainingTest.a;

import org.testng.annotations.Test;

public class MethodChainingTest2 {

		public void methodD() {
			System.out.println("Chaining stopped");
		}

		@Test
		public void sampleTest(){
		   {
			   a().b().c();
			}
			}

}
  