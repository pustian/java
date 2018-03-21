package tian.pusen.jdk.demo.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class ThreadPoolSample {
	public static void main(String[] args) {
		// 创建一个可重用固定线程集合的线程池，以共享的无界队列方式来运行这些线程。
		// 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。  定长线程池的大小最好根据系统资源进行设置。如Runtime.getRuntime().availableProcessors()
//		ExecutorService threadPool = Executors.newFixedThreadPool(3);
		// 耗完内存
		// 创建一个可根据需要创建新线程的线程池，但是在以前构造的线程可用时将重用它们。 
		// 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
		// 线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
//		ExecutorService threadPool = Executors.newCachedThreadPool();
		// 创建一个使用单个 worker 线程的 Executor，以无界队列方式来运行该线程。
		// 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		for (int i = 1; i < 5; i++) {
			final int taskID = i;
			threadPool.execute(new Runnable() {
				public void run() {
					for (int i = 1; i < 5; i++) {
						try {
							Thread.sleep(20);// 为了测试出效果，让每次任务执行都需要一定时间
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("第" + taskID + "次任务的第" + i + "次执行");
					}
				}
			});
		}
		threadPool.shutdown();// 任务执行完毕，关闭线程池
		
		// 创建一个定长线程池，支持定时及周期性任务执行。 
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);  
		scheduledThreadPool.schedule(new Runnable(){
			public void run() {
				System.out.println("delay 3 seconds");
			}}, 3, TimeUnit.SECONDS);

		System.out.println("End Main");
	}
}
