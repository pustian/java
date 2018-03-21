/**
 * 
 */
package tian.pusen.jdk.demo.bitoperator;

/**
 * <p> Title:BitOutput </p>
 * <p> Description:       </p>
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年10月20日 上午9:40:00
 */
public class BitOutput {
	public static void main(String[] args) {
		System.out.println("\nbyte===[0x42] [0x42 0x42]");
		outputBit((byte)0x42);	
		System.out.println();
		outputBit(new byte[]{(byte)0x42, (byte)0xf1} );
		System.out.println();
		System.out.println("\nshort===[0x42] [0x42 0x42]");
		outputBit((short)0x42);
		System.out.println();
		outputBit(new short[]{(short)0x42, (short)0xf1} );
		System.out.println();
		System.out.println("\nint===[0x42] [0x42 0x42]");
		outputBit((int)0x42);
		System.out.println();
		outputBit(new int[]{(int)0x42, (int)0xf1} );
		System.out.println();
		System.out.println("\nlong===[0x42] [0x42 0x42]");
		outputBit((long)0x42);
		System.out.println();
		outputBit(new long[]{(long)0x42, (long)0xf1} );
		System.out.println();
	}
	// byte *char short int long 
	public static void outputBit(byte b) {
		for(int i =7; i>=0; i--) {
			System.out.print( b>>i & 0x1 );
			if(i%4 == 0)
				System.out.print(' ');
		}
	}
	public static void outputBit(byte[] bytes) {
		for(int i=0;i<bytes.length; i++)
			outputBit(bytes[i]);
	}
	public static void outputBit(short s) {
		for(int i =15; i>=0; i--) {
			System.out.print( s>>i & 0x1 );
			if(i%4 == 0)
				System.out.print(' ');
		}
	}
	public static void outputBit(short[] shorts) {
		for(int i=0;i<shorts.length; i++)
			outputBit(shorts[i]);
	}
	public static void outputBit(int integer) {
		for(int i =31; i>=0; i--) {
			System.out.print( integer>>i & 0x1 );
			if(i%4 == 0)
				System.out.print(' ');
		}
	}
	public static void outputBit(int[] integers) {
		for(int i=0;i<integers.length; i++)
			outputBit(integers[i]);
	}
	public static void outputBit(long l) {
		for(int i =63; i>=0; i--) {
			System.out.print( l>>i & 0x1 );
			if(i%4 == 0)
				System.out.print(' ');
		}
	}
	public static void outputBit(long[] longs) {
		for(int i=0;i<longs.length; i++)
			outputBit(longs[i]);
	}
}
