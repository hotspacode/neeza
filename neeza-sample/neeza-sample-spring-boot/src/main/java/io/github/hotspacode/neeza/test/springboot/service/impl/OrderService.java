package io.github.hotspacode.neeza.test.springboot.service.impl;

import io.github.hotspacode.neeza.base.annotation.NeezaMock;
import io.github.hotspacode.neeza.test.springboot.pojo.Order;
import io.github.hotspacode.neeza.test.springboot.pojo.OrderItem;
import io.github.hotspacode.neeza.test.springboot.service.IOrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
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
