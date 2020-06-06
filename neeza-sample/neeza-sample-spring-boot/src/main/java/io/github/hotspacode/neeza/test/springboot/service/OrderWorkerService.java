package io.github.hotspacode.neeza.test.springboot.service;

import com.chinaredstar.ordercenter.api.common.OrderResult;
import com.chinaredstar.ordercenter.vo.orderworker.CreateOrderWorkerVo;
import com.chinaredstar.ordercenter.vo.orderworker.CreateWorkerVo;
import io.github.hotspacode.neeza.base.annotation.NeezaMock;
import io.github.hotspacode.neeza.base.core.MockSpy;
import io.github.hotspacode.neeza.base.dto.MockTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;

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


    @Transactional
    public OrderResult addOrderWorker() {
        ArrayList var1 = new ArrayList();
        MockTransport var2 = MockSpy.getMockData(Thread.currentThread().getStackTrace()[1], var1, "");
        if (var2.isMocked()) {
            return (OrderResult)MockTransport.getObject(var2);
        }
        OrderResult orderResult = OrderResult.newError();
        CreateOrderWorkerVo orderWorkerVo = new CreateOrderWorkerVo();
        Iterator var3 = orderWorkerVo.getWorkerVos().iterator();

        while (var3.hasNext()) {
            CreateWorkerVo var4 = (CreateWorkerVo) var3.next();
            System.out.println(var4);
        }

        return orderResult;
    }

    @Transactional
    public OrderResult addOrderWorker1() {
        ArrayList var1 = new ArrayList();
        MockTransport var2 = MockSpy.getMockData(Thread.currentThread().getStackTrace()[1], var1, "");
        if (var2.isMocked()) {
            return (OrderResult)MockTransport.getObject(var2);
        }
        OrderResult orderResult = OrderResult.newError();
        CreateOrderWorkerVo orderWorkerVo = new CreateOrderWorkerVo();
        return orderResult;
    }

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
