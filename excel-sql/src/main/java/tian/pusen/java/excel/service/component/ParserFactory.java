package tian.pusen.java.excel.service.component;

import tian.pusen.java.excel.service.parse.Parser;
import tian.pusen.java.excel.service.parse.impl.ExcelParser;

/**
 * Created by tianpusen on 2017/3/24.
 */
public final class ParserFactory {
    public static Parser getParser() {
        Parser parser = new ExcelParser();
        return parser ;
    }
}
