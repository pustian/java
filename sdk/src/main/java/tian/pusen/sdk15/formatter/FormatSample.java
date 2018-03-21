/**
 * 
 */
package tian.pusen.sdk15.formatter;

import tian.pusen.sdk15.enums.Month;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p> Title:FormatSample </p>
 * <p> Description:       </p>
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年11月3日 下午6:04:32
 */
public final class FormatSample {
	public static void printf() {
        //printf
        String filename = "this is a file";
        try {
            File file = new File(filename);
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            int i = 1;
            while ((line = reader.readLine()) != null) {
                System.out.printf("Line %d: %s%n", i++, line);
            }
        } catch (Exception e) {
            System.err.printf("Unable to open file named '%s': %s",
                    filename, e.getMessage());
        }
    }
    public static void stringFormat() {
        // Format a string containing a date.
        Calendar calendar = new GregorianCalendar(1995, Month.Aug.getValue(), 23);
        String  formatCalendat = String.format("Duke's Birthday: %1$tm %1$te,%1$tY", calendar);
        // -> s == "Duke's Birthday: May 23, 1995"
        System.out.println(formatCalendat);
    }
    public static void formatter() {
        StringBuilder sb = new StringBuilder();
        // Send all output to the Appendable object sb
        Formatter formatter = new Formatter(sb, Locale.US);
        // Explicit argument indices may be used to re-order output.
        formatter.format("%4$2s %3$2s %2$2s %1$2s", "a", "b", "c", "d");
        // -> " d  c  b  a"
        // Optional locale as the first argument can be used to get
        // locale-specific formatting of numbers.  The precision and width can be
        // given to round and align the value.
        formatter.format(Locale.FRANCE, "e = %+10.4f", Math.E);
        // -> "e =    +2,7183"
        // The '(' numeric flag may be used to format negative numbers with
        // parentheses rather than a minus sign.  Group separators are
        // automatically inserted.
        formatter.format("Amount gained or lost since last statement: $ %(,.2f",
                6217.58);
        // -> "Amount gained or lost since last statement: $ (6,217.58)"
    }
    public static void messageFormat() {
        String msg = "欢迎光临，当前（{0}）等待的业务受理的顾客有{1}位，请排号办理业务！";
        MessageFormat mf = new MessageFormat(msg);
        String fmsg = mf.format(new Object[]{new Date(), 35});
        System.out.println(fmsg);
    }
    public static void dateFormat(){
        String str = "2010-1-10 17:39:21";
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            System.out.println(format.format(format.parse(str)));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        formatter();
        stringFormat();
        messageFormat();
        dateFormat();
        printf();
    }
}
