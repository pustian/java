package tian.pusen.structure.facade;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * Date: 2017/12/24 17:07
 *
 * @author tianpusen
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class MainApp {
    public static void main(String[] args) {
        Computer computer= new Computer();
        computer.startup();
        System.out.println("==================");
        computer.shutdown();
    }
}
