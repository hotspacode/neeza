package io.github.hotspacode.neeza.core.config;

import com.alibaba.fastjson.parser.ParserConfig;
import io.github.hotspacode.neeza.core.NeezaMockConstant;

public class NeezaMockConfig {
    public static final String DEFAULT_CHARSET = "UTF-8";

    public static void init(){
        System.getProperties().setProperty(NeezaMockConstant.SIMPLE_MOCK_VM_PACKAGE_NAME, "io/github/hotspacode/neeza/test");
        System.getProperties().setProperty(NeezaMockConstant.SIMPLE_MOCK_VM_SERVER_URL, "http://localhost:8080/neeza/mock/string/");
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }
}
