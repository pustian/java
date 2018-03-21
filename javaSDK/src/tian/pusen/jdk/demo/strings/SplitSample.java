/**
 * 
 */
package tian.pusen.jdk.demo.strings;

/**
 * <p> Title:SplitSample </p>
 * <p> Description:       </p>
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年10月28日 上午10:18:42
 */
public final class SplitSample {
	public static void main(String[] args) {
		usage01();
		usage02();
		usage03();
		usage04();
		usage05();
	}
	// output : [Real, , How, To]
	public static void usage01() {
		String testString = "Real  How To"; // extra space
		System.out.println(java.util.Arrays.toString(testString.split(" ")));
	}
    // output : [Real, How, To]
	public static void usage02() {
		String testString = "Real  How To"; // extra space
	    System.out.println
	       (java.util.Arrays.toString(testString.split("\\s+")));
	}
	//Since String.split() is based on regular expression, you can make some complex operations with a simple call! 
	// output : [, RealHowto, , java-0438.html, , usage of String.split()]
	// note : extra empty elements :-(
	public static void usage03() {
		String testString = "{RealHowto}{java-0438.html}{usage of String.split()}";
		  System.out.println
		     (java.util.Arrays.toString(testString.split("[{}]")));
	}

	// To split a long string into into fixed-length parts.
	public static void usage04() {
		String testString = "0123456789012345678901";
		System.out.println(java.util.Arrays.toString(testString
				.split("(?<=\\G.{3})")));
		// output : [012, 345, 678, 901, 234, 567, 890]
	}

	// To split but keep the separator :
	public static void usage05() {
		String testString = "RealHowto!java-0438.html!usage of String.split()!";
		System.out.println(java.util.Arrays.toString(testString
				.split("(?<=[!])")));
		// output : [RealHowto!, java-0438.html!, usage of String.split()!]
	}

}
