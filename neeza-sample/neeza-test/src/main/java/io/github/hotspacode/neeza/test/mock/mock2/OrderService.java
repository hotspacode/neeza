package io.github.hotspacode.neeza.test.mock.mock2;

import io.github.hotspacode.neeza.base.annotation.NeezaMock;

import java.util.List;
import java.util.Map;

@NeezaMock
public class OrderService implements IOrderService {
    @Override
    public Order getOrder(Long orderId) {
        Order order = new Order();
        order.setId(orderId);
        order.setMarketName("service内部");
        order.setShopName("shop内部name");
        return order;
    }

    @Override
    public List<OrderItem> listOrderItem() {
        return null;
    }

    @Override
    public Map orderMap(String key, String value) {
        return null;
    }
}
