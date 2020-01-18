package io.github.hotspacode.neeza.test.mock;

import io.github.hotspacode.neeza.base.config.NeezaBaseConfig;
import io.github.hotspacode.neeza.base.annotation.NeezaMock;

@NeezaMock
public class MockForReturnTypeServiceTest {
    public static void main(String[] args) {
        NeezaBaseConfig.init();
//        MockForReturnTypeService mockService = new MockForReturnTypeService();
        CommonMessageService commonMessageService = new CommonMessageService();
//        System.out.println("执行结果:" + commonMessageService.getString("1111111111","fsdddddddddd"));
        System.out.println("执行结果:" + commonMessageService.testInt("1111111111",null));
//        commonMessageService.getVoid("1111111111","fsdddddddddd");
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
