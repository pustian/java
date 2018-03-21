package tian.pusen.java.excel.service.component;

import tian.pusen.java.excel.common.enums.FileTemplateEnum;
import tian.pusen.java.excel.service.create.Creater;
import tian.pusen.java.excel.service.create.impl.IbatisCodeCreater;
import tian.pusen.java.excel.service.create.impl.MybatisCodeCreater;

/**
 * Created by tianpusen on 2017/3/24.
 */
public class CreaterFactory {
    public static Creater getCreater(FileTemplateEnum fileTemplateEnum) {
        Creater creater = null;
        switch (fileTemplateEnum) {
            case CodeExcelTemplate:
                creater = new IbatisCodeCreater();
                break;
            case SQLExcelTemplate:
                creater = new MybatisCodeCreater();
                break;
        }
        return creater ;
    }
}
