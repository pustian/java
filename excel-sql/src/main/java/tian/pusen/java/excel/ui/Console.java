package tian.pusen.java.excel.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tianpusen on 2017/3/24.
 */
public class Console {
    private static final Logger logger = LoggerFactory.getLogger(Executor.class);

    public static void main(String[] args) {
        logger.debug("Main Start");
        String excelTemplateFileName = "./src/main/resources/Code模板.xlsx" ;
        String createFileDirectory = "./target/codeDir";
        Executor executor = new Executor(excelTemplateFileName, createFileDirectory);
        executor.service();
        logger.debug("Main End");
    }
}
