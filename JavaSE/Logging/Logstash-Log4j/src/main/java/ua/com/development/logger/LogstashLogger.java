package ua.com.development.logger;

import org.apache.log4j.Logger;

public class LogstashLogger {

    private static final Logger LOGGER = Logger.getLogger("logstash");

    public static void main(String[] args) {
        LOGGER.info("Info message");
    }
}
