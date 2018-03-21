package tian.pusen.app;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tian.pusen.info.LoggerDisplay;

/**
 * Created by tianpusen on 2017/4/19.
 */
public class Log4j2XMLMain {
    private final static Logger logger = LogManager.getLogger();

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
        logger.fatal("Output logger  at level fatal {}", para);
        logger.error("Output logger  at level error {}",para);
        logger.warn("Output logger  at level warn {}",para);
        //        if(logger.isInfoEnabled() ) no need
        logger.info("Output logger  at level info {}",para);
        //        if(logger.isDebugEnabled() )
        logger.debug("Output logger  at level debug {}",para);
        //        if(logger.isTraceEnabled() )
        logger.trace("Output logger  at level trace {}",para);
    }
}
