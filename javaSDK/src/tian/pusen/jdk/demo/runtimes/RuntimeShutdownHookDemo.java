/**
 * 
 */
package tian.pusen.jdk.demo.runtimes;

/**
 * <p>
 * Title:RuntimeShutdownHookDemo
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: Masapay
 * </p>
 * 
 * @author: tianpusen
 * @Date : 2016年10月19日 下午3:36:37
 */
public class RuntimeShutdownHookDemo {

	public static void main(String[] args) {
		// 定义线程1
		Thread thread1 = new Thread() {
			public void run() { try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
			} System.out.println("thread1...");}
		};

		// 定义线程2
		Thread thread2 = new Thread() {
			public void run() { try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			} System.out.println("thread2..."); }
		};

		// 定义关闭线程
		Thread shutdownThread = new Thread() {
			public void run() { System.out.println("shutdownThread..最后运行该线程 ==>类似于C中注册的 atexit()."); }
		};

		// jvm关闭的时候先执行该线程钩子
		Runtime.getRuntime().addShutdownHook(shutdownThread);
		thread1.start();
		thread2.start();
	}

}
