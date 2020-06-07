package io.github.hotspacode.neeza.test.springboot.service;

import io.github.hotspacode.neeza.base.annotation.NeezaMock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * EnhancerAdapter
 * Created by h on 2017/11/13.
 */
@Service
@NeezaMock
public class OrderWorkerService {

    private static Logger logger = LoggerFactory.getLogger(OrderWorkerService.class);


    private Object orderMapper;

    private Object orderWorkerMapper;

    private Object openOrderService;

    private Object orderExtendMapper;




    /*public OrderResult addOrderWorker1() {
        ArrayList var1 = new ArrayList();
        MockTransport var2 = MockSpy.getMockData(Thread.currentThread().getStackTrace()[1], var1, "");
        if (var2.isMocked()) {
            return (OrderResult)MockTransport.getObject(var2);
        } else {
            OrderResult var6 = OrderResult.newError();
            CreateOrderWorkerVo var7 = new CreateOrderWorkerVo();
            if (var7.getWorkerVos() != null) {
                Iterator var3 = var7.getWorkerVos().iterator();

                while(var3.hasNext()) {
                    CreateWorkerVo var4 = (CreateWorkerVo)var3.next();
                    OrderWorker var5 = new OrderWorker();
                    var5.setCreateDate(new Date());
                    var5.setDeleteFlag(false);
                    var5.setWorkerId(var4.getWorkerId());
                    var5.setWorkerName(var4.getWorkerName());
                    this.orderWorkerMapper.hashCode();
                }
            }

            return var6;
        }
    }*/


}
