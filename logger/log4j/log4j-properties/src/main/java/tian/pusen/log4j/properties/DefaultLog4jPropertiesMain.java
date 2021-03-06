/**
 * Created by tianpusen on 2017/4/18.
 */
package tian.pusen.log4j.properties;

import org.apache.log4j.Logger;
import tian.pusen.log4j.info.LoggerDisplay;

public class DefaultLog4jPropertiesMain {
    private final static Logger logger = Logger.getLogger(DefaultLog4jPropertiesMain.class);

    public static void main(String[] args) {
        displayAllLevelLogger("default para");
        LoggerDisplay.display();
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
