package tian.pusen.jdk.logger.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Created by tianpusen on 2017/4/17.
 */
public class PropertiesLoggingMain {
    private final static Logger logger = Logger.getLogger(PropertiesLoggingMain.class.getName());
    private static LogManager logManager = LogManager.getLogManager();

    static {
        InputStream inputStream = null;
        try {
            //读取配制文件
            inputStream = PropertiesLoggingMain.class.getClassLoader().getResourceAsStream("log.properties");
            logManager.readConfiguration(inputStream);
            //添加Logger
            logManager.addLogger(logger);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        displayAllLevelLogger(" 1st");

        System.out.println("To set Level 2 Level.FINE is not available at the console rather than file");
        logger.setLevel(Level.FINE);
        displayAllLevelLogger(" 2nd");
    }

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
