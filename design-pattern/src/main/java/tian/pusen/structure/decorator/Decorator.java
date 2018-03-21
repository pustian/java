package tian.pusen.structure.decorator;

import jdk.management.resource.internal.inst.SocketDispatcherRMHooks;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * Date: 2017/12/24 17:05
 *
 * @author tianpusen
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Decorator implements Sourceable {
    private Sourceable sourceable;
    public Decorator(Sourceable sourceable) {
        super();
        this.sourceable = sourceable;
    }

    @Override
    public void method() {
        System.out.println("Decorator begin ===");
        sourceable.method();
        System.out.println("Decorator end ===");
    }
}
