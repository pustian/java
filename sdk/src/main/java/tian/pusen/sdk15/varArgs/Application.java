/**
 * 
 */
package tian.pusen.sdk15.varArgs;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> Title:Application </p>
 * <p> Description:       </p>
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年11月3日 下午5:37:59
 */
public final class Application {
	public static void main(String[] args) {
		Object[] objArr = {true, 0x1, '0', (short)10, 1, 1L, 1.1f, 1.2, "hello"};
		@SuppressWarnings("rawtypes")
		List<Class> classList = getObject(objArr);
		System.out.println(objArr.length);
		System.out.println(classList.size());
	}
	
	@SuppressWarnings("rawtypes")
	public static List<Class> getObject(Object ... objs) {
		List<Class> classList = new ArrayList<Class>();
		for(Object obj: objs) {
			if(obj != null) {
				System.out.println(obj.getClass());
				classList.add(obj.getClass());
			}
		}
		return classList;
	}
	
}
