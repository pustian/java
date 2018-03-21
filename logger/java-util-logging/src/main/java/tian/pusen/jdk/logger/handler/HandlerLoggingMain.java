package tian.pusen.jdk.logger.handler;

import java.io.IOException;
import java.util.logging.*;

/**
 * Created by tianpusen on 2017/4/17.
 */
public class HandlerLoggingMain {
    private final static Logger logger = Logger.getLogger(HandlerLoggingMain.class.getName());

    public static void main(String[] args) throws IOException {
        ConsoleHandler consoleHandler =new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        consoleHandler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return record.getLevel()+":" + record.getMessage()+"\n";
            }
        });
        logger.addHandler(consoleHandler);
        displayAllLevelLogger(" 1st");

        FileHandler fileHandler = new FileHandler("c://workspace/idea/logger/java-util-logging/target/log%u.log");
        fileHandler.setLevel(Level.FINE);
        fileHandler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return record.getLevel()+":" + record.getMessage()+"\n";
            }
        });
        logger.addHandler(fileHandler);
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
