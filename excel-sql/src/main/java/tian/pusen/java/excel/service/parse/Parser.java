package tian.pusen.java.excel.service.parse;

import tian.pusen.java.excel.entity.CodeContext;

import java.util.List;

/**
 * Created by tianpusen on 2017/3/20.
 */
public interface Parser {
    /**
     * 文件类型依赖于文件名后缀判断。现在默认为excel以后增加新类型，在具体判断
     * @param filename
     * @return
     */
   List<CodeContext> parse(String filename);
//    List<? extends Context> parse(InputStream inputStream);
}
