package tian.pusen.creater.singleton;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * Date: 2017/11/1 13:15
 *
 * @author tianpusen
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Singleton00 {
    //  /* 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载 */
    private static Singleton00 instance = null;

    // /* 私有构造方法，防止被实例化 */
    private Singleton00() {
    }

    // /* 静态工程方法，创建实例 */
    public static Singleton00 getInstance() {
        if (instance == null) {
            instance = new Singleton00();
        }
        return instance;
    }

    // /* 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */
    public Object readResolve() {
        return instance;
    }
}
