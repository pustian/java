package tian.pusen.jdk.demo.thread.basic;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
// Callable接口类似于Runnable，从名字就可以看出来了，但是Runnable不会返回结果，并且无法抛出返回结果的异常，
// 而Callable功能更强大一些，被线程执行后，可以返回值，这个返回值可以被Future拿到，也就是说，Future可以拿到异步执行任务的返回值
public class CallableAndFuture {
	public static void main(String[] args) {
		System.out.println("sample2");
//		sample1();
		sample2();
		System.out.println("Main End");
    }
	
	public static void sample1(){
        Callable<Integer> callable = new Callable<Integer>() {
            public Integer call() throws Exception {
                return new Random().nextInt(100);
            }
        };
        FutureTask<Integer> future = new FutureTask<Integer>(callable);
        new Thread(future).start();
        try {
            Thread.sleep(5000);// 可能做一些事情
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
	}
	// Threadpool调用
	public static void sample2(){
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<Integer> future = threadPool.submit(new Callable<Integer>() {
            public Integer call() throws Exception {
                return new Random().nextInt(100);
            }
        });
        try {
            Thread.sleep(5000);// 可能做一些事情
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
	}
	// 返回多个值
	public static void sample3(){
		 ExecutorService threadPool = Executors.newCachedThreadPool();
	     CompletionService<Integer> cs = new ExecutorCompletionService<Integer>(threadPool);
	     for(int i = 1; i < 5; i++) {
	    	 final int taskID = i;
	    	 cs.submit(new Callable<Integer>() {
	    		 public Integer call() throws Exception {
	    			 return taskID;
	    		 }
	    	});
	     }
	     // 可能做一些事情
	     for(int i = 1; i < 5; i++) {
	     	try {
	     		System.out.println(cs.take().get());
	     	} catch (InterruptedException e) {
	     		e.printStackTrace();
	     	} catch (ExecutionException e) {
	     		e.printStackTrace();
	     	}
	     }
	}
}
