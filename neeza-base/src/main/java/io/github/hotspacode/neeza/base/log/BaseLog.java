package io.github.hotspacode.neeza.base.log;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseLog {
    protected static Handler makeLogger(String logName, Logger logger) {

        Handler handler = null;
        String fileName = "/logs/" + logName;

        try {
            handler = new FileHandler(fileName + ".%d", 1024 * 1024 * 200, 4, true);
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (handler != null) {
            logger.addHandler(handler);
        }
        logger.setLevel(Level.ALL);
        return handler;
    }

    protected static void log(Logger logger, Level level, String detail, Object... params) {
        if (detail == null) {
            return;
        }
        if (params.length == 0) {
            logger.log(level, detail);
        } else {
            logger.log(level, detail, params);
        }
    }

    protected static void log(Logger logger, Level level, String detail, Throwable throwable) {
        if (detail == null) {
            return;
        }

        logger.log(level, detail, throwable);
    }

}
