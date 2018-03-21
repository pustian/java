package javaABC.demo.thread.basic;

public class SynchronizedUsage {
	public static void main(String[] args) {  
        final Outputter output = new Outputter();  
        new Thread() {  
            public void run() {  
                output.output("zhangsan");  
            }  
        }.start();        
        new Thread() {  
            public void run() {  
                output.output("lisi");  
            }  
        }.start();  
        System.out.println("主线程开始执行！");  
    }  
}
class Outputter {  
	// static volatile int i = 0, j = 0;  // volatile static变量的线程安全
	// synchronized 没有的话线程不安全
    public synchronized void output(String name) {  
        // TODO 为了保证对name的输出不是一个原子操作，这里逐个输出name的每个字符  
        for(int i = 0; i < name.length(); i++) {  
            System.out.print(name.charAt(i));  
            // Thread.sleep(10);  
        }  
    }  
}  
