/**
 * 
 */
package tian.pusen.jdk.demo.strings;

import java.util.StringTokenizer;

/**
 * <p>
 * Title:StringTokenizerSample
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: Masapay
 * </p>
 * 
 * @author: tianpusen
 * @Date : 2016年11月7日 上午9:57:23
 */
//public StringTokenizer(String str)
//public StringTokenizer(String str, String delim)
//public StringTokenizer(String str, String delim, boolean returnDelims)
//第一个参数就是要分隔的String，第二个是分隔字符集合，第三个参数表示分隔符号是否作为标记返回，如果不指定分隔字符，默认的是：”\t\n\r\f”
public final class StringTokenizerSample {
	public static void main(String[] args) {
		String s = new String("The Java platform is the ideal platform for network computing");
		StringTokenizer st = new StringTokenizer(s);
		System.out.println( "Token Total: " + st.countTokens() );
		while( st.hasMoreElements() ){
			System.out.println( st.nextToken() );
		}
	}
}
