package io.github.hotspacode.neeza.base.log;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NeezaLog extends BaseLog {
    private static final Logger logger = Logger.getLogger("NEEZA");
    private static final String FILE_NAME = "neeza.log";
    private static Handler logHandler = null;

    static {
        logHandler = makeLogger(FILE_NAME, logger);
    }

    public static void info(String detail, Object... params) {
        log(logger, Level.INFO, detail, params);
    }

    public static void info(String detail, Throwable e) {
        log(logger, Level.INFO, detail, e);
    }

    public static void warn(String detail, Object... params) {
        log(logger, Level.WARNING, detail, params);
    }

    public static void warn(String detail, Throwable e) {
        log(logger, Level.WARNING, detail, e);
    }

}
