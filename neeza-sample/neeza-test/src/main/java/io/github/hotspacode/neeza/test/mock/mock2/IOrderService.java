package io.github.hotspacode.neeza.test.mock.mock2;


import java.util.List;
import java.util.Map;

public interface IOrderService {
    Order getOrder(Long orderId);

    List<OrderItem> listOrderItem();

    Map orderMap(String key, String value);


}
