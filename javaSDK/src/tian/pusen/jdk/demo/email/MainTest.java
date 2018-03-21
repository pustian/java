/**
 * 
 */
package javaABC.demo.email;

/**
 * <p> Title:MainTest </p>
 * <p> Description:       </p>
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年10月19日 下午5:29:17
 */
public final class MainTest {

	/**
	 * @param args
	 */
    public static void main(String[] args) {  
        Mail mail = new Mail();  
        mail.setHost("smtp.qq.com"); // 设置邮件服务器,如果不用163的,自己找找看相关的  
        mail.setSender("15097634@qq.com");  
        mail.setReceiver("15097634@qq.com"); // 接收人  
        mail.setUsername("15097634@qq.com"); // 登录账号,一般都是和邮箱名一样吧  
        mail.setPassword("tian0821"); // 发件人邮箱的登录密码  
        mail.setSubject("Jmail Api test");  
        mail.setMessage("Jmail Api test content");  
//        new MailUtil().send(mail);  
    }  

}
