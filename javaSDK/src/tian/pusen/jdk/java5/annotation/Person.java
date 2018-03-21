/**
 * 
 */
package tian.pusen.java5.annotation;

import java.io.Serializable;

/**
 * <p> Title:Person </p>
 * <p> Description:       </p>
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年11月4日 下午1:20:28
 */
public final class Person implements Serializable {
	private static final long serialVersionUID = 5801588405122289941L;
	private String name;
	@SexCheck
	private char sex;
	private int age;
	private String address;
	
	
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final char getSex() {
		return sex;
	}
	public final void setSex(char sex) {
		this.sex = sex;
	}
	public final int getAge() {
		return age;
	}
	public final void setAge(int age) {
		this.age = age;
	}
	public final String getAddress() {
		return address;
	}
	public final void setAddress(String address) {
		this.address = address;
	}
	
}
