package tian.pusen.java.excel.service.component;

import tian.pusen.java.excel.common.enums.FileTemplateEnum;
import tian.pusen.java.excel.service.parse.Parser;
import tian.pusen.java.excel.service.parse.impl.CodeExcelParser;
import tian.pusen.java.excel.service.parse.impl.SQLExcelParser;

/**
 * Created by tianpusen on 2017/3/24.
 */
public final class ParserFactory {
    public static Parser getParser(FileTemplateEnum fileTemplateEnum) {
        Parser parser = null;
        switch (fileTemplateEnum) {
            case CodeExcelTemplate:
                parser = new CodeExcelParser();
                break;
            case SQLExcelTemplate:
                parser = new SQLExcelParser();
                break;
        }
        return parser ;
    }
}
