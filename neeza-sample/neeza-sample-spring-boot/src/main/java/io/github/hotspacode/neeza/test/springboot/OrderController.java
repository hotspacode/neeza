package io.github.hotspacode.neeza.test.springboot;

import io.github.hotspacode.neeza.base.annotation.NeezaMock;
import io.github.hotspacode.neeza.test.springboot.pojo.Order;
import io.github.hotspacode.neeza.test.springboot.pojo.OrderItem;
import io.github.hotspacode.neeza.test.springboot.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@NeezaMock
@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private IOrderService orderService;


    @GetMapping("getOrder")
    public Order getOrder(@RequestParam(value = "orderId") Long orderId) {
        return orderService.getOrder(orderId);
    }

    @GetMapping("listOrderItem")
    public List<OrderItem> listOrderItem() {
        return orderService.listOrderItem();
    }

    @GetMapping("orderMap")
    public Map orderMap(@RequestParam(value = "key")String key,@RequestParam(value = "value") String value) {
        return orderService.orderMap(key,value);
    }

}
