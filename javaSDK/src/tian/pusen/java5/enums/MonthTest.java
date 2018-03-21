/**
 * 
 */
package tian.pusen.java5.enums;

/**
 * <p> Title:MonthTest </p>
 * <p> Description:       </p>
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年11月3日 下午5:20:15
 */
public class MonthTest {
	public static void main(String[] args) {
		@SuppressWarnings("rawtypes")
		Enum enums4 = Month.Apr;
		Month month4 = Month.Apr;
		if(enums4 == month4)
			System.out.println(month4);
	}
}
