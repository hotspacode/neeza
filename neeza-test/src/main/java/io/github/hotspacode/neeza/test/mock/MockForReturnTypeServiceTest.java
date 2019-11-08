package io.github.hotspacode.neeza.test.mock;

import io.github.hotspacode.neeza.core.annotation.SimpleMock;
import io.github.hotspacode.neeza.core.config.SimpleMockConfig;

@SimpleMock
public class MockForReturnTypeServiceTest {
    public static void main(String[] args) {
        SimpleMockConfig.init();
        MockForReturnTypeService mockService = new MockForReturnTypeService();
        CommonMessageService commonMessageService = new CommonMessageService();
//        commonMessageService.testOrderResult();

//        Map<String, String> map = mockService.testMap();
//        OrderResult orderResult = mockService.testOrderResult(null);
//        String s = mockService.testString();
//        int i = mockService.testInt();
//        mockService.testVoid();
//        Double aDouble = mockService.testDouble();
//        double testdouble = mockService.testdouble();
//        List list = mockService.testArrayList();
        System.out.println("done");
    }


}
