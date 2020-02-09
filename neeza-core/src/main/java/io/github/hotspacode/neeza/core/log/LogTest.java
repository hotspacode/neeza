package io.github.hotspacode.neeza.core.log;

import io.github.hotspacode.neeza.base.annotation.NeezaMock;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogTest {
    private static final Logger neezaLogger = Logger.getLogger("neezaLogger");


    public static void main(String[] args) {
        System.out.println(NeezaMock.class.toString().replace(".", "/").replace("interface ", ""));
        neezaLogger.log(Level.INFO,"neeza log:哈哈哈");
    }
}
