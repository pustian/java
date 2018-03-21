package tian.pusen.structure.adapter.interfaceadapter;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * Date: 2017/11/1 22:23
 *
 * @author tianpusen
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public  abstract class Wrapper implements Targetable {
    @Override
    public void method() {
        System.out.println("Wrapper this is method at " + getClass());
    }

    @Override
    public void anotherMethod() {
        System.out.println("Wrapper this is anotherMethod at " + getClass());
    }
}
