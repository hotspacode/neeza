package io.github.hotspacode.neeza.client;

import com.alibaba.fastjson.parser.ParserConfig;
import io.github.hotspacode.neeza.base.util.NeezaConstant;
import org.junit.Before;

public class BaseTest {

    @Before
    public void testBefore() {
//        System.getProperties().setProperty(NeezaConstant.SIMPLE_MOCK_VM_PACKAGE_NAME, "io/github/hotspacode/neeza/test");
//        System.getProperties().setProperty(NeezaConstant.SIMPLE_MOCK_VM_SERVER_URL, "http://localhost:8080/neeza/mock/string/");
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }

}
