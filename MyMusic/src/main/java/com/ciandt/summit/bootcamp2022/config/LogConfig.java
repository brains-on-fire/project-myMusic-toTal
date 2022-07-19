package com.ciandt.summit.bootcamp2022.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogConfig {
    public final Logger log;

    public LogConfig(Object object) {
        this.log = LoggerFactory.getLogger(object.getClass());
    }

    public void create(LogType logType, String message) {
        if (logType == LogType.INFO) {
            log.info(message);
        } else if (logType == LogType.WARN) {
            log.warn(message);
        } else if (logType == LogType.ERROR) {
            log.error(message);
        }
    }
}
