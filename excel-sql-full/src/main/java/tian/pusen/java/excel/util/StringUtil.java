package tian.pusen.java.excel.util;

/**
 * Created by tianpusen on 2017/3/23.
 */
public class StringUtil {

    public static boolean isBlank(String string) {
        return null == string ||  "".equals(string.trim()) ;
    }

    public static boolean isNotBlank(String string) {
        return null != string &&  ! ("".equals(string.trim()) );
    }

}
