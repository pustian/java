package tian.pusen.java.excel.service.parse;

import tian.pusen.java.excel.entity.CodeContext;

import java.util.List;

/**
 * Created by tianpusen on 2017/3/24.
 */
public abstract class AbstractParser implements Parser {
    @Override
    public List<CodeContext> parse(String filename) {
        // 判断文件类型。
        List<CodeContext> contextList = null;
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
    protected  abstract List<CodeContext> parseExcelDetail(String filename);


}
