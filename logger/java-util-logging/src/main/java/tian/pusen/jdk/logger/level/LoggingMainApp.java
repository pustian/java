/**
 * Created by tianpusen on 2017/4/17.
 */
package tian.pusen.jdk.logger.level;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingMainApp {
    private final static Logger logger = Logger.getLogger("LoggingMainApp");

    public static void main(String[] args) {
        displayAllLevelLogger(" 1st");

        System.out.println("To set Level 2 Level.FINE is not available at the console rather than file");
        logger.setLevel(Level.FINE);
        displayAllLevelLogger(" 2nd");
    }

    /**
     * logger默认的级别是INFO，比INFO更低的日志将不显示。
     *  SEVERE（最高值）        WARNING        INFO
     *  CONFIG    FINE        FINER        FINEST（最低值）
     */
    public static void displayAllLevelLogger(String para){
        logger.severe("Output logger  at level " + Level.SEVERE  + para);
        logger.warning("Output logger  at level " + Level.WARNING + para );
        logger.info("Output logger  at level " + Level.INFO + para );
        logger.config("Output logger  at level " + Level.CONFIG + para );
        logger.fine("Output logger  at level " + Level.FINE + para );
        logger.finer("Output logger  at level " + Level.FINER + para );
        logger.finest("Output logger  at level " + Level.FINEST + para );
    }
}
