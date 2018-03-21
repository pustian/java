/**
 * 
 */
package javaABC.demo.email;

import java.io.Serializable;

/**
 * <p> Title:Mail </p>
 * <p> Description:       </p>
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年10月19日 下午5:10:26
 */
public final class Mail implements Serializable {
	private static final long serialVersionUID = -2709397574653440720L;
	public static final String ENCODING = "UTF-8";
    private String host; // 服务器地址  
    private String sender; // 发件人的邮箱  
    private String receiver; // 收件人的邮箱  
    private String name; // 发件人昵称  
    private transient String username; // 账号  
    private transient String password; // 密码  
    private String subject; // 主题  
    private String message; // 信息(支持HTML)  
    
	public final String getHost() {
		return host;
	}
	public final void setHost(String host) {
		this.host = host;
	}
	public final String getSender() {
		return sender;
	}
	public final void setSender(String sender) {
		this.sender = sender;
	}
	public final String getReceiver() {
		return receiver;
	}
	public final void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final String getUsername() {
		return username;
	}
	public final void setUsername(String username) {
		this.username = username;
	}
	public final String getPassword() {
		return password;
	}
	public final void setPassword(String password) {
		this.password = password;
	}
	public final String getSubject() {
		return subject;
	}
	public final void setSubject(String subject) {
		this.subject = subject;
	}
	public final String getMessage() {
		return message;
	}
	public final void setMessage(String message) {
		this.message = message;
	}
}
