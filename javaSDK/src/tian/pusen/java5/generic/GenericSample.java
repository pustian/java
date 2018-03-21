/**
 * 
 */
package tian.pusen.java5.generic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> Title:GenericSample </p>
 * <p> Description:       </p>
 * 所谓类型擦除指的就是Java源码中的范型信息只允许停留在编译前期，而编译后的字节码文件中将不再保留任何的范型信息。
 * 也就是说，范型信息在编译时将会被全部删除，其中范型类型的类型参数则会被替换为Object类型，并在实际使用时强制转换为指定的目标数据类型。
 * 而C++中的模板则会在编译时将模板类型中的类型参数根据所传递的指定数据类型生成相对应的目标代码。
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年11月3日 下午3:48:48
 */
public final class GenericSample {
	// 通配符类型：避免unchecked警告，问号表示任何类型都可以接受
	// 限制类型
	public static void main(String[] args) {
//		List<int> intList = new ArrayList(); // 必须为对象
		List<Integer> intList = new ArrayList<Integer>();
		intList.add(10); // 自动包装
		intList.add(20); // 自动包装
		List<Double> doubleList = new ArrayList<Double>();
		doubleList.add(12.3);
		List<BigDecimal> decimalList = new ArrayList<BigDecimal>();
		decimalList.add(new BigDecimal("89.4566"));
		List<? extends Number> numberList = new ArrayList<>();
		numberList.add(null);
//		numberList.addAll(intList);// 编译不过
		numberList = intList;
		System.out.println("numberList");
		for(Number number: numberList)
			System.out.println(number);
		
		List<? extends Number> numberList2 = null;
		numberList2 = doubleList;
		System.out.println("numberList2");
		for(Number number: numberList2)
			System.out.println(number);
		
		swap(numberList, numberList2);
		System.out.println("numberList");
		for(Number number: numberList)
			System.out.println(number);
		System.out.println("numberList2");
		for(Number number: numberList2)
			System.out.println(number);
		int a = 10; 
		int b = 20;
		System.out.println("out a"+a);
		swap(a, b);
		System.out.println("out a"+a);
	}
	
	public static <T> void swap(T a, T b) {
		T t = a;
		a = b;
		b = t;
		System.out.println("inner a"+a);
	}
}
