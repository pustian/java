package tian.pusen.java.excel.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tian.pusen.java.excel.common.enums.FileTemplateEnum;

/**
 * Created by tianpusen on 2017/3/24.
 */
public class Console {
    private static final Logger logger = LoggerFactory.getLogger(Executor.class);

    public static void main(String[] args) {
        logger.debug("Main Start");
        String excelTemplateFileName = "./src/main/resources/Code模板.xlsx" ;
        FileTemplateEnum fileTemplateEnum = FileTemplateEnum.CodeExcelTemplate;
        String createFileName= "./target/codeDir";
        Executor executor = new Executor(excelTemplateFileName, fileTemplateEnum, createFileName);
        executor.service();
        logger.debug("Main End");
    }
}
