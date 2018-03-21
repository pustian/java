/**
 * 
 */
package tian.pusen.sdk15.enums;

/**
 * <p> Title:Month </p>
 * <p> Description:       </p>
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年11月3日 下午5:07:50
 */
public enum Month {
	Jan(1, "January","一月"),
	Feb(2, "February","二月"),
	Mar(3, "March","三月"),
	Apr(4, "April","四月"),
	May(5, "May","五月"),
	June(6, "June","六月"),
	July(7, "July","七月"),
	Aug(8, "Aguest","八月"),
	Sept(9, "September","九月"),
	Oct(10, "October","十月"),
	Nov(11, "March","十一月"),
	Dec(12, "Dember","十二月"),
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
	public final Month getByCode(int value){
		Month result = null;
		for(Month month: Month.values()) {
			if(value == month.getValue() ) {
				result = month;
			}
		}
		return result;
	}

	@Override
	public String toString() {
		return "Month{" +
				"value=" + value +
				", description='" + description + '\'' +
				", description_zh='" + description_zh + '\'' +
				'}';
	}
}
