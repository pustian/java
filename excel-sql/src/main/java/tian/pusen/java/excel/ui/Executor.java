package tian.pusen.java.excel.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tian.pusen.java.excel.entity.CodeContext;
import tian.pusen.java.excel.service.component.CreaterFactory;
import tian.pusen.java.excel.service.component.ParserFactory;
import tian.pusen.java.excel.service.create.Creater;
import tian.pusen.java.excel.service.parse.Parser;

import java.util.List;

/**
 * Created by tianpusen on 2017/3/20.
 */
public class Executor {
    private static final Logger logger = LoggerFactory.getLogger(Executor.class);

    private Parser parser;
    private Creater creater;

    private String excelTemplateFileName ;
    private String createFileDirectory;

    public Executor(String excelTemplateFileName, String createFileDirectory){
        this.excelTemplateFileName = excelTemplateFileName;
        this.createFileDirectory = createFileDirectory;
        parser = ParserFactory.getParser();
        creater = CreaterFactory.getCreater();
    }

    /**
     *  做解析和生成代码或是脚本
     */
    public void service() {
        List<CodeContext> contextList = null;
        contextList = parser.parse(excelTemplateFileName);
        for(CodeContext codeContext :contextList) {
            logger.debug("解析结果{}", codeContext);
            creater.create(codeContext, createFileDirectory);
        }
    }

    public Parser getParser() {
        return parser;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public Creater getCreater() {
        return creater;
    }

    public void setCreater(Creater creater) {
        this.creater = creater;
    }

    public String getExcelTemplateFileName() {
        return excelTemplateFileName;
    }

    public void setExcelTemplateFileName(String excelTemplateFileName) {
        this.excelTemplateFileName = excelTemplateFileName;
    }

    public String getCreateFileName() {
        return createFileDirectory;
    }

    public void setCreateFileName(String createFileName) {
        this.createFileDirectory = createFileName;
    }
}
