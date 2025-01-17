package practice;

public class MethodChainingTest {
	
	public static MethodChainingTest a() {
		System.out.println("This is method A");
		return new MethodChainingTest();
	}
	public static MethodChainingTest b() {
		System.out.println("This is method B");
		return new MethodChainingTest();
	}
	public static MethodChainingTest c() {
		System.out.println("This is method C");
		new MethodChainingTest2().methodD();
		return new MethodChainingTest();
	}

	
}
