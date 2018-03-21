/**
 * 
 */
package tian.pusen.jdk.demo.format;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p> Title:DoubleFormatSample </p>
 * <p> Description:       </p>
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年10月19日 下午3:50:45
 */
public class FormatSample { 
    //java5 format
	public static void main(String[] args) {
// parse用的比较少
//		messageFormat();
//		decimalFormat();
		dateFormat();
	}
	public static void messageFormat() {
		String message = "oh, {0,number,#.#} {1} is a pig. Using like this:'{2}' is unavailable.";
		Object[] array = new Object[]{new Double(3.1415), "ZhangSan", "天"};
		String value = MessageFormat.format(message, array);
		System.out.println(value);
	}
	public static void decimalFormat() {
	    DecimalFormat df1 = new DecimalFormat("0.0");
	    DecimalFormat df2 = new DecimalFormat("#.#");
	    DecimalFormat df3 = new DecimalFormat("000.000");
	    DecimalFormat df4 = new DecimalFormat("###.###");
	    System.out.println(df1.format(12.34) + "\tvalue:12.34 pattern"+"0.0");
	    System.out.println(df2.format(12.34) + "\tvalue:12.34 pattern"+"#.#");
	    System.out.println(df3.format(12.34) + "\tvalue:12.34 pattern"+"000.000");
	    System.out.println(df4.format(12.34) + "\tvalue:12.34 pattern"+"###.###");

	    DecimalFormat format = new DecimalFormat("###,####.000");
	    System.out.println(format.format(1234567890.123456) + "\tvalue:1234567890.123456 pattern"+"###,####.000");

//	    Locale.setDefault(Locale.US);
	    DecimalFormat usFormat = new DecimalFormat("###,###.##");
	    System.out.println(usFormat.format(1234567890.123456) + "\tvalue:1234567890.123456 pattern"+"###,###.##");
	    System.out.println(usFormat.format(new BigDecimal("1234567890.123456")) + "\tvalue:BigDecimal 1234567890.123456 pattern"+"###,###.##");

	    DecimalFormat zhiFormat = new DecimalFormat();
	    zhiFormat.applyPattern("0.000E0000");
	    System.out.println(zhiFormat.format(10000) + "\tvalue:10000 pattern"+"0.000E0000");
	    System.out.println(zhiFormat.format(12345678.345) + "\tvalue:12345678.345 pattern"+"0.000E0000");

	    DecimalFormat percentFormat = new DecimalFormat();
	    percentFormat.applyPattern("#0.000%");
//	    System.out.println(percentFormat.format(0.3052222) + "\tvalue:0.3052222 pattern"+"#0.000%");
	    System.out.println(percentFormat.format(0.003052222) + "\tvalue:0.003052222 pattern"+"#0.000%");
	    percentFormat.applyPattern("#.000%");
	    System.out.println(percentFormat.format(0.003052222) + "\tvalue:0.003052222 pattern"+"#.000%");
	    percentFormat.applyPattern("#.###%");
	    System.out.println(percentFormat.format(0.003052222) + "\tvalue:0.003052222 pattern"+"#.###%");
	}
	public static void dateFormat(){
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
		System.out.println(dateFormat.format(new Date()));
		System.out.println(dateFormat.format(Calendar.getInstance().getTime()));
	}

}
