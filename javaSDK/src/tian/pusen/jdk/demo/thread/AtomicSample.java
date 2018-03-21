/**
 * 
 */
package tian.pusen.jdk.demo.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * <p> Title:AtomicSample </p>
 * <p> Description:       </p>
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年10月25日 下午5:57:47
 */
public final class AtomicSample {
	// For simple multi-threaded code,
	public static void main(String[] args) {
		AtomicInteger aInt = new AtomicInteger(); // Create with default value 0

		AtomicInteger aInt01 = new AtomicInteger(1); // Create with initial value 1
		AtomicIntegerArray aIntArray = new AtomicIntegerArray(10); // Create array of specific length
		AtomicIntegerArray aIntArray01 = new AtomicIntegerArray(new int[] {1, 2, 3}); // Initialize array with another array
		
		// There is a notable exception that there is no float and double types. 
	}

}
