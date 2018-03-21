package tian.pusen.java.excel.service.parse;

import tian.pusen.java.excel.pojo.Context;

import java.util.List;

/**
 * Created by tianpusen on 2017/3/24.
 */
public abstract class AbstractParser implements Parser {
    @Override
    public List<? extends Context> parse(String filename) {
        // 判断文件类型。
        List<? extends Context> contextList = null;
        if(filename.endsWith("xls")||filename.endsWith("xlsx")) {
            contextList = parseExcelDetail(filename);
        }
        return contextList;
    }

    /**
     * Excel类型文件的具体类实现
     * @param filename
     * @return
     */
    protected  abstract List<? extends Context> parseExcelDetail(String filename);


}
