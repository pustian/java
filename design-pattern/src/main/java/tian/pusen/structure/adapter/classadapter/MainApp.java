package tian.pusen.structure.adapter.classadapter;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * Date: 2017/11/1 22:27
 *
 * @author tianpusen
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class MainApp {
    public static void main(String[] args) {
        Targetable targetable = new ClassAdapter();
        targetable.method();
        targetable.anotherMethod();
    }
}
