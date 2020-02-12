package io.github.hotspacode.neeza.test.springboot.service;

import io.github.hotspacode.neeza.base.annotation.NeezaMock;
import io.github.hotspacode.neeza.test.springboot.pojo.Order;
import io.github.hotspacode.neeza.test.springboot.pojo.OrderItem;

import java.util.List;
import java.util.Map;

public interface IOrderService {
    Order getOrder(Long orderId);

    List<OrderItem> listOrderItem();

    Map orderMap(String key, String value);


}
