package ua.com.development.logger;

import org.apache.log4j.Logger;

public class Log4jLogger {

    private static final Logger LOGGER = Logger.getLogger(Log4jLogger.class);

    public static void main(String[] args) {
        LOGGER.debug("Debug message");
        LOGGER.info("Info message");
    }
}
