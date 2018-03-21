package tian.pusen.java5.boxing;

public final class BoxSample {
	// Boolean、Byte、Short、Character、Integer、Long、Float、Double
	public static void main(String[] args) {
		Integer integer = 19; // boxing
		int i = integer;      // unboxing 编译无误
		
		Object obj = 10;
		System.out.println(obj.getClass());
//		System.out.println(20.getClass());  // 编译错
	}
}
