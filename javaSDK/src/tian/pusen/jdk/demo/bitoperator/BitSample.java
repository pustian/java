/**
 * 
 */
package tian.pusen.jdk.demo.bitoperator;

/**
 * <p> Title:BitSample </p>
 * <p> Description:       </p>
 * 	//	~ 按位非（NOT） 
	//	& 按位与（AND） 	| 按位或（OR）	^ 按位异或（XOR）
	//	>> 右移 	>>> 无符号右移	<<左移 
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年10月20日 上午8:50:10
 */
public final class BitSample {
	public static void main(String[] args) {
		shift();

	}

	
	
	//	操作 	结果 	说明
	//	00110010 << 2 	11001000 	右边始终填充0
	//	00110010 >> 2 	00001100 	结果一样
	//	00110010 >>> 2 	00001100
	//	10110010 >> 2 	11101100 	结果不同
	//	10110010 >>> 2 	00101100
	public static void shift(){
		int integer= 0xf1234567;
		System.out.println(integer);
		BitOutput.outputBit(integer);
		System.out.println();

		// /2^3
		int lefShift = integer >> 3;
		System.out.println(lefShift);
		BitOutput.outputBit(lefShift);
		System.out.println();

		// /2^3
		System.out.println("short operation is unavailable");
		int  unsignedLefShift = integer >>> 3;
		System.out.println(unsignedLefShift);
		BitOutput.outputBit(unsignedLefShift);
		System.out.println();

		// *2^3
		int rightShift = integer << 3;
		System.out.println(rightShift);
		BitOutput.outputBit(rightShift);
		System.out.println();
	}
	//	& 按位与（AND） 	| 按位或（OR）	^ 按位异或（XOR）
	//	按位与 	按位或 	按位异或
	//	0 & 0 = 0 	0 | 0 = 0 	0 ^ 0 = 0
	//	0 & 1 = 0 	0 | 1 = 1 	0 ^ 1 = 1
	//	1 & 0 = 0 	1 | 0 = 1 	1 ^ 0 = 1
	//	1 & 1 = 1 	1 | 1 = 1 	1 ^ 1 = 0
	
}
