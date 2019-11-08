package io.github.hotspacode.neeza.client;

import com.alibaba.fastjson.parser.ParserConfig;
import org.junit.Before;
import io.github.hotspacode.neeza.core.SimpleMockConstant;

public class BaseTest {

    @Before
    public void testBefore() {
        System.getProperties().setProperty(SimpleMockConstant.SIMPLE_MOCK_VM_PACKAGE_NAME, "io/github/hotspacode/neeza/test");
        System.getProperties().setProperty(SimpleMockConstant.SIMPLE_MOCK_VM_SERVER_URL, "http://localhost:8080/neeza/mock/string/");
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }

}
