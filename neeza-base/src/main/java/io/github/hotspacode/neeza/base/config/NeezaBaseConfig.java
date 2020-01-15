package io.github.hotspacode.neeza.base.config;


import io.github.hotspacode.neeza.base.util.NeezaConstant;

public class NeezaBaseConfig {
    public static final String DEFAULT_CHARSET = "UTF-8";

    public static void init(){
        System.getProperties().setProperty(NeezaConstant.SIMPLE_MOCK_VM_PACKAGE_NAME, "io/github/hotspacode/neeza/test");
        System.getProperties().setProperty(NeezaConstant.SIMPLE_MOCK_VM_SERVER_URL, "http://localhost:8080/neeza/mock/string/");
    }
}
