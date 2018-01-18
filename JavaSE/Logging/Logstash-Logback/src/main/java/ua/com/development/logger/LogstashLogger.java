package ua.com.development.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogstashLogger {

    private static final Logger LOGGER = LoggerFactory.getLogger("logstash");

    public static void main(String[] args) {
        LOGGER.info("Info message");
    }
}
