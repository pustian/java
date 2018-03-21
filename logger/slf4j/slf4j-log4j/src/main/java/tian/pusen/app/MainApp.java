package tian.pusen.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tian.pusen.info.LoggerDisplay;

/**
 * Created by tianpusen on 2017/4/21.
 */
public class MainApp {
    private final static Logger logger = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) {
        displayAllLevelLogger("1st");
        LoggerDisplay.display();
    }
    /**
     * FATAL       0    ERROR      3    WARN       4
     * INFO         6   DEBUG      7    TRACE
     * 支持占位符
     * @param para
     */
    public static void displayAllLevelLogger(String para){
        logger.error("Output logger  at level error {}",  para);
        logger.warn("Output logger  at level warn {}",  para);
        //        if(logger.isInfoEnabled() ) no need
        logger.info("Output logger  at level info {}",  para);
        //        if(logger.isDebugEnabled() )
        logger.debug("Output logger  at level debug {}",  para);
        //        if(logger.isTraceEnabled() )
        logger.trace("Output logger  at level trace {}",  para);
    }
}
