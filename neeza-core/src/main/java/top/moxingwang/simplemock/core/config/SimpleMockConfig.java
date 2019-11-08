package top.moxingwang.simplemock.core.config;

import com.alibaba.fastjson.parser.ParserConfig;
import top.moxingwang.simplemock.core.SimpleMockConstant;

public class SimpleMockConfig {
    public static void init(){
        System.getProperties().setProperty(SimpleMockConstant.SIMPLE_MOCK_VM_PACKAGE_NAME, "top/moxingwang/simplemock/test");
        System.getProperties().setProperty(SimpleMockConstant.SIMPLE_MOCK_VM_SERVER_URL, "http://localhost:8080/simple-mock/mock/string/");
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }
}
