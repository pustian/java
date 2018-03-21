package tian.pusen.structure.proxy;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * Date: 2017/12/24 17:20
 *
 * @author tianpusen
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Proxy implements Sourceable {
    private Sourceable sourceable;

    public Proxy() {
        super();
        this.sourceable = new Source();
    }

    public Proxy(Class clazz) throws IllegalAccessException, InstantiationException {
        super();
//        sourceable = new Source();
        sourceable = (Sourceable) clazz.newInstance();
    }

    @Override
    public void method() {
        before();
        sourceable.method();
        after();
    }

    private static void before() {
        System.out.println("proxy begin===");
    }
    private static void after() {
        System.out.println("proxy end=====");
    }
}
