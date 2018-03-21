/**
 * 
 */
package tian.pusen.java5.enums;

/**
 * <p> Title:Month </p>
 * <p> Description:       </p>
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年11月3日 下午5:07:50
 */
public enum Month {
	Jan(0, "January","一月"),
	Feb(0, "February","二月"),
	Mar(0, "March","三月"),
	Apr(0, "April","四月"),
	May(0, "May","五月"),
	June(0, "June","六月"),
	July(0, "July","七月"),
	Aug(0, "Aguest","八月"),
	Sept(0, "September","九月"),
	Oct(0, "October","十月"),
	Nov(0, "March","十一月"),
	Dec(0, "Dember","十二月"),
	;
	private final int value;
	private final String description;
	private final String description_zh;
//	private String description_jp;
	private Month(final int value, final String description, final String description_zh) {
		this.value = value;
		this.description = description;
		this.description_zh = description_zh;
	}
	
	public final int getValue() {
		return value;
	}
	public final String getDescription() {
		return description;
	}
	public final String getDescription_zh() {
		return description_zh;
	}
}
