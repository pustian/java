package tian.pusen.java7.exception;

import java.io.FileInputStream;
import java.io.IOException;

public class ExceptionSample {
    public void read(String filename) throws Exception {  
        FileInputStream input = null;  
        Exception exception = null;  
        try {  
            input = new FileInputStream(filename);  
        } catch (IOException ex) {  
        	exception = ex;  
        } finally {  
            if (input != null) {  
                try {  
                    input.close();  
                    int i= 10/0;    // runtime 在Jdk7以前会被抑制掉
                } catch (IOException|RuntimeException ex) {   // JDK7写法
                    if (exception != null) {    //此处的区别 。// runtime 在Jdk7以前会被抑制掉
                    	exception.addSuppressed(ex);  
                    }  else {  
                    	exception = ex;  
                    }  
                }  
            }  
            if (exception != null) {  
                throw exception;  
            }  
        }  
    }  
    
}
