/**
 * 
 */
package tian.pusen.jdk.demo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * <p> Title:CalendarSample </p>
 * <p> Description:       </p>
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年10月20日 下午5:14:41
 */
public final class CalendarSample {

	public static void main(String[] args) {
		lastDateAtPrecedingMonth();
	}
	// 上月最后一天
	public static Date lastDateAtPrecedingMonth() {
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH);
		calendar.add(Calendar.MONTH, month-1); // 上月
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH) ) ;
		Date date = calendar.getTime();
		System.out.println(new Date(calendar.getTimeInMillis()));
		System.out.println(date);
		return date;
	}
	
	public static Date getDateAtYYYYMMDDHHMMSS(int year, int month, int day, int hh, int mm, int ss){
		Calendar calendar = Calendar.getInstance();
		Calendar gregorianCalendar = new GregorianCalendar();
		Calendar gregorianCalendarAtSpecificDay = new GregorianCalendar(2016, Calendar.JANUARY, 1);
		Calendar gregorianCalendarAtSpecificDayAndTime = new GregorianCalendar(year, month-1, day, hh, mm, ss);
		return gregorianCalendarAtSpecificDayAndTime.getTime();
	}
}
