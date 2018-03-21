package javaABC.demo.thread.basic;

//注意，如果存在synchronized线程同步的话，线程让步不会释放锁(监视器对象)。
public class YieldSample {
	public static void main(String[] args) throws InterruptedException {  
        // 创建线程对象  
        YieldThread t1 = new YieldThread("t1");  
        YieldThread t2 = new YieldThread("t2");  
        // 启动线程  
        t1.start();  
        t2.start();  
        // 主线程休眠100毫秒  
        Thread.sleep(100);  
        // 终止线程  
        t1.interrupt();  
        t2.interrupt();  
        System.out.println("主线程开始执行！");  
    }  
}  
class YieldThread extends Thread {  
    int i = 0;  
    public YieldThread(String name) {  
        super(name);  
    }  
    public void run() {  
        while(!isInterrupted()) {  
            System.out.println(getName() + "执行了" + ++i + "次");  
            if(i % 10 == 0) {// 当i能对10整除时，则让步  
                Thread.yield();  
            }  
        }  
    }  
}  
