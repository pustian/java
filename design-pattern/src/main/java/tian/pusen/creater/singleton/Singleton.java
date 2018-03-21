package tian.pusen.creater.singleton;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * Date: 2017/11/1 13:22
 *
 * @author tianpusen
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Singleton {
    // 防止实例化
    private Singleton(){}

    // 使用内部类来维护单例
    private static class SingletonFactory {
        private static Singleton instance = new Singleton();
    }

    // 获取实例
    public static Singleton getInstance() {
        return SingletonFactory.instance;
    }

    // 如果该对象被用于序列化，可以保证对象在序列化前后保持一致
    public Object readResolve() {
        return getInstance();
    }
}
