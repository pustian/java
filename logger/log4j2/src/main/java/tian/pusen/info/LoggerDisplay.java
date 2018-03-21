package tian.pusen.info;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by tianpusen on 2017/4/19.
 */
public class LoggerDisplay {
    private final static Logger logger = LogManager.getLogger(LoggerDisplay.class);
    public static void display(){
        logger.fatal("Output logger  at level fatal {}", "class LoggerDisplay");
        logger.error("Output logger  at level error {}","class LoggerDisplay");
        logger.warn("Output logger  at level warn {}","class LoggerDisplay");
        //        if(logger.isInfoEnabled() ) no need
        logger.info("Output logger  at level info {}","class LoggerDisplay");
        //        if(logger.isDebugEnabled() )
        logger.debug("Output logger  at level debug {}","class LoggerDisplay");
        //        if(logger.isTraceEnabled() )
        logger.trace("Output logger  at level trace {}","class LoggerDisplay");

    }
}
