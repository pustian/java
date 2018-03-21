package javaABC.demo.thread.basic;

// 我们发现，存在成员变量的类用于多线程时是不安全的，不安全体现在这个成员变量可能发生非原子性的操作，而变量定义在方法内也就是局部变量是线程安全的。
public class InnerVarThreadSafe {
	public static void main(String[] args) {
		Runnable runnable = new Runnable() { 
//			Count count = new Count();  // Not safe
            public void run() {  
            	Count count = new Count(); // Safe  
                count.count();  
            }  
        };  
        for(int i = 0; i < 10; i++) {
        	new Thread(runnable).start();  
        }
	}
}
class Count {
	public void count() {
		int num = 0;
		for(int i = 1; i <= 10; i++) 
			num += i; 
		System.out.println(Thread.currentThread().getName() + "-" + num);		
	}
}  

