/**
 * 
 */
package tian.pusen.jdk.demo.asserts;

/**
 * <p> Title:AssertSample </p>
 * <p> Description:       </p>
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年10月26日 上午9:50:49
 */
// 相生效须在java 参数中添加 -ea
// 1、assert condition;
// 2、asser condition:expr;
//Exception in thread "main" java.lang.AssertionError
//at javaABC.demo.asserts.AssertSample.main(AssertSample.java:22)
public final class AssertSample {
	public static void main(String[] args) {
		System.out.println("begin");
		assert args.length > 1;
		
		System.out.println("end");	
	}

}
