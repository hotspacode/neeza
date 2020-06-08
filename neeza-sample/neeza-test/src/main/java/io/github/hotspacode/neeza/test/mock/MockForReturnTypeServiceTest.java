package io.github.hotspacode.neeza.test.mock;

import io.github.hotspacode.neeza.base.annotation.NeezaMock;
import io.github.hotspacode.neeza.base.dto.MockData;
import io.github.hotspacode.neeza.base.util.NeezaConstant;
import io.github.hotspacode.neeza.spy.NeezaServer;
import io.github.hotspacode.neeza.test.mock.mock2.IOrderService;
import io.github.hotspacode.neeza.test.mock.mock2.OrderService;

@NeezaMock
public class MockForReturnTypeServiceTest {
    public static void main(String[] args) {
        IOrderService orderService = new OrderService();
        orderService.getOrder(1L);
        NeezaServer.start("localhost", NeezaConstant.DEFAULT_SERVER_PORT,"io.github.hotspacode.neeza");
        new ItemPromotionVo();
        new MockData();
//        MockForReturnTypeService mockService = new MockForReturnTypeService();
        CommonMessageService commonMessageService = new CommonMessageService();
//        System.out.println("执行结果:" + commonMessageService.getString("1111111111","fsdddddddddd"));
        System.out.println("执行结果:" + commonMessageService.testInt("1111111111", null));
        System.out.println("执行结果:" + commonMessageService.load("1111111111", null));
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
