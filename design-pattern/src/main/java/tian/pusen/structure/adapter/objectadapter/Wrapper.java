package tian.pusen.structure.adapter.objectadapter;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * Date: 2017/11/1 22:23
 *
 * @author tianpusen
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Wrapper implements Targetable {
    private Source source;
    public Wrapper(Source source){
        this.source = source;
    }

    @Override
    public void method() {
        source.method();
    }

    @Override
    public void anotherMethod() {
        System.out.println("this is anotherMethod at " + getClass());
    }
}
