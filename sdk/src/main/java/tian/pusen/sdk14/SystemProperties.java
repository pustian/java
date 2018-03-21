package tian.pusen.sdk14;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * Date: 2018/2/6 14:45
 *
 * @author tianpusen
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class SystemProperties {
    public static void main(String[] args) {
        Properties properties = System.getProperties();
        Iterator<Map.Entry<Object, Object>> iterator = properties.entrySet().iterator();
        while(iterator.hasNext() ) {
            Map.Entry<Object, Object> entry= iterator.next();
            System.out.println(entry.getKey() + "==" + entry.getValue() );
        }
    }
}
