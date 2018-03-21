package tian.pusen.java.excel.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tian.pusen.java.excel.common.enums.FileTemplateEnum;
import tian.pusen.java.excel.service.component.CreaterFactory;
import tian.pusen.java.excel.service.component.ParserFactory;
import tian.pusen.java.excel.service.create.Creater;
import tian.pusen.java.excel.service.parse.Parser;
import tian.pusen.java.excel.pojo.Context;

import java.util.List;

/**
 * Created by tianpusen on 2017/3/20.
 */
public class Executor {
    private static final Logger logger = LoggerFactory.getLogger(Executor.class);

    private Parser parser;
    private Creater creater;

    private String excelTemplateFileName ;
    private FileTemplateEnum fileTemplateEnum;
    private String createFileName;

    public Executor(String excelTemplateFileName, FileTemplateEnum fileTemplateEnum, String createFileName){
        this.excelTemplateFileName = excelTemplateFileName;
        this.fileTemplateEnum = fileTemplateEnum;
        this.createFileName = createFileName;
        parser = ParserFactory.getParser(fileTemplateEnum);
        creater = CreaterFactory.getCreater(fileTemplateEnum);
    }

    /**
     *  做解析和生成代码或是脚本
     */
    public void service() {
        // operteType 转换
        logger.debug("Excel Template:{} fileTemplateEnum:{}  Directory/File:{}",
            excelTemplateFileName ,fileTemplateEnum, createFileName );
        List<? extends Context> contextList = null;
        contextList = parser.parse(excelTemplateFileName);
        for(Context context :contextList) {
            logger.debug("解析结果{}", context);
            creater.create(context, createFileName);
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

    public FileTemplateEnum getFileTemplateEnum() {
        return fileTemplateEnum;
    }

    public void setFileTemplateEnum(FileTemplateEnum fileTemplateEnum) {
        this.fileTemplateEnum = fileTemplateEnum;
    }

    public String getCreateFileName() {
        return createFileName;
    }

    public void setCreateFileName(String createFileName) {
        this.createFileName = createFileName;
    }
}
