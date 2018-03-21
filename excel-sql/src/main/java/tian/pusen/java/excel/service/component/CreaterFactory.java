package tian.pusen.java.excel.service.component;

import tian.pusen.java.excel.service.create.Creater;
import tian.pusen.java.excel.service.create.impl.MybatisCreater;

/**
 * Created by tianpusen on 2017/3/24.
 */
public class CreaterFactory {
    public static Creater getCreater() {
        Creater creater = new MybatisCreater();
        return creater ;
    }
}
