package io.github.hotspacode.neeza.ide.service;

public interface StaticMockService {
    String mapMock = "{\"@type\":\"java.util.HashMap\",\"key1\":\"string1\",\"key2\":\"string2\",\"key3\":\"string3\"}";
    String orderResultMock = "{\"@type\":\"com.chinaredstar.ordercenter.api.common.OrderResult\",\"code\":\"200\",\"dataMap\":{\"@type\":\"com.chinaredstar.ordercenter.module.order.Order\",\"coupons\":[],\"invoiceInfos\":[],\"isAdditionReview\":false,\"itemPromotions\":[],\"orderItem\":{\"itemAttributes\":[]},\"orderItems\":[],\"orderStatusVersions\":[],\"payableAmount\":1111110,\"paymentLines\":[],\"priceDefferenceOrders\":[],\"promOrders\":[],\"promotions\":[],\"refoundOrders\":[],\"serialNumber\":\"323232\",\"workers\":[]},\"message\":\"Success\",\"success\":true}";
    String intMock = "10";
    String doubleMock = "10.1";
    String testArrayList = "[\"1\",\"2\",\"3\"]";

}
