package tian.pusen.sdk14;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * Date: 2018/1/30 17:06
 *
 * @author tianpusen
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Application {
    public static void main(String[] args) {
        System.out.println(checkMobileNo("18221485450"));
        System.out.println(checkMobileNo("18221485450 "));
        System.out.println(checkMobileNo("1222148545x"));
        System.out.println(checkMobileNo("11221485450"));
        System.out.println(checkMobileNo("12221485450"));
        System.out.println(checkMobileNo("19221485450"));
    }
    public static boolean checkMobileNo(String mobileNo) {
        Pattern p = Pattern.compile("^1[3-9]\\d{9}$");
        Matcher m = p.matcher(mobileNo);
        boolean bool = false;
        if(m.matches()) {
            bool = true;
        }
        return bool;
    }
}
