package ua.com.development.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackLogger {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogbackLogger.class);

    public static void main(String[] args) {
        LOGGER.debug("Debug message");
        LOGGER.info("Info message");
    }
}
