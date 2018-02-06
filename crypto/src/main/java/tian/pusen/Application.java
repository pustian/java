package tian.pusen;

import com.sun.org.apache.xpath.internal.SourceTree;
import tian.pusen.jdk.des.DesEncryptor;

import java.awt.*;
import java.util.Base64;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * Date: 2018/1/25 14:20
 *
 * @author tianpusen
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Application {
    public static final Base64.Decoder decoder = Base64.getDecoder();
    public static final Base64.Encoder encoder = Base64.getEncoder();

    public static void main(String[] args) {
//        splitTest();

//        String name = "艾斯卡尔·塞莱";
//        String name2 = "艾斯卡尔.塞莱";
//        boolean bool = isSameName(name, name2);
//        System.out.println(bool);
    }
    public static void splitTest() {
        String string = "a and b  or c";
        //        String str[] = string.split("and|or");
        String str[] = string.split(" ");
        for(String s:str) {
            System.out.println("["+s+"]");
        }
    }
    public static boolean isSameName(String dbName, String inputName) {
        String dbNameArr[] = dbName.split("·|•|\\.");
        String inputNameArr[] = inputName.split("·|•|\\.");
        boolean bool = true;
        if(dbNameArr.length ==inputNameArr.length) {
            for(int i=0; i<dbNameArr.length; i++) {
                System.out.println(""+i+"  ["+dbNameArr[i] +"] [" +inputNameArr[i]+"]");
                if(null != dbNameArr[i] && null != inputNameArr[i] ) {
                    if(bool) {
                        bool = bool && dbNameArr[i].equals(inputNameArr[i]);
                    }
                }
            }
        }
        return bool;
    }
}
