package tian.pusen.jdk.demo.thread.pool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutorSample {

	public static void main(String[] args) {
		ScheduledThreadPoolExecutor exec=new  ScheduledThreadPoolExecutor( 1 );  
        
        exec.scheduleAtFixedRate(new  Runnable(){ //每隔一段时间就触发异常   
            @Override   
            public   void  run() {  
            	System.out.println("如果有异常会终止该线程");
                throw   new  RuntimeException();  
            }}, 0 ,  5000 , TimeUnit.MILLISECONDS); // 马上开始执， 执行完成后5000毫秒后再次执行  
          
        exec.scheduleAtFixedRate(new  Runnable(){ //每隔一段时间打印系统时间，证明两者是互不影响的   
            @Override   
            public   void  run() { 
                System.out.println("输出当前时间"+new SimpleDateFormat("yyyymmdd").format(new Date()));  
            }}, 1000 ,  2000 , TimeUnit.MILLISECONDS);  // 1000秒后首次，执行完成后间隔2000毫秒执行
    }  
}
