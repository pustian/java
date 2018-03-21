package tian.pusen.sdk15.boxing;

public final class Application {
	// Boolean、Byte、Short、Character、Integer、Long、Float、Double
	public static void main(String[] args) {
		Integer integer = 19; // boxing
		int i = integer;      // unboxing 编译无误

		Object obj = 10;
		System.out.println(obj.getClass());
		System.out.println(int.class);
//		System.out.println(10.getClass());  // 编译错

		test();
	}

	//java在申请一个大于-128小于127的数时，其实是从cache中直接取出来用的，
	// 如果不在这个范围则是new了一个Integer对象。
	// 见IntegerCache
	public static void test() {
		int i1 = 127, i2 = 127, i3 = 128, i4 = 128;
		System.out.println(i1  == i2);
		System.out.println(i3  == i4);

		Integer integer1 = 127, integer2 = 127, integer3 = 128, integer4 = 128;
		System.out.println(integer1  == integer2);
		System.out.println(integer1.equals(integer2));
		System.out.println(integer3  == integer4);  // false
		System.out.println(integer3.equals(integer4));
	}

}
