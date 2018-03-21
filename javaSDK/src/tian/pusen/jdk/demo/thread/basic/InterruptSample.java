package javaABC.demo.thread.basic;

public class InterruptSample {
	public static void main(String[] args) throws InterruptedException {  
        MyThread t = new MyThread("MyThread");  
        t.start();  
        Thread.sleep(100);// 睡眠100毫秒  
        t.interrupt();// 中断t线程  需要在run中判断
        System.out.println("主线程开始执行！");  
    }  
}  
class MyThread extends Thread {  
    int i = 0;  
    public MyThread(String name) {  
        super(name);  
    }  
    public void run() {  
        while(!isInterrupted()) { // 当前线程没有被中断，则执行  
            System.out.println(getName() + getId() + "执行了" + ++i + "次");  
        }  
    }  
}  