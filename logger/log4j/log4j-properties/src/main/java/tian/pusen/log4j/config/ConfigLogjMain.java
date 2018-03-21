package tian.pusen.log4j.config;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Created by tianpusen on 2017/4/18.
 */
public class ConfigLogjMain {
    private final static Logger logger = Logger.getLogger(ConfigLogjMain.class);

    public static void main(String[] args) {
        BasicConfigurator.configure(); // 自动快速地使用缺省Log4j环境。
//        PropertyConfigurator.configure(String configFilename); 读取使用Java的特性文件编写的配置文件。
//        DOMConfigurator.configure(String filename); 读取XML形式的配置文件。
        displayAllLevelLogger("config");
    }

    /**
     * FATAL       0    ERROR      3    WARN       4
     * INFO         6   DEBUG      7    TRACE
     * @param para
     */
    public static void displayAllLevelLogger(String para){
        logger.fatal("Output logger  at level fatal " + para);
        logger.error("Output logger  at level error " + para);
        logger.warn("Output logger  at level warn " + para);
        //        if(logger.isInfoEnabled() ) no need
        logger.info("Output logger  at level info " + para);
        //        if(logger.isDebugEnabled() )
        logger.debug("Output logger  at level debug " + para);
        //        if(logger.isTraceEnabled() )
        logger.trace("Output logger  at level trace " + para);
    }
}

