package io.github.hotspacode.neeza.test.springboot.service;

import com.alibaba.fastjson.JSON;

import com.chinaredstar.ordercenter.api.common.OrderResult;
import com.chinaredstar.ordercenter.exception.OrderCenterRuntimeException;
import com.chinaredstar.ordercenter.message.ResultCode;

import com.chinaredstar.ordercenter.module.order.Order;
import com.chinaredstar.ordercenter.module.orderworker.OrderWorker;
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

import java.util.*;

/**EnhancerAdapter
 * Created by h on 2017/11/13.
 */
@Service
@NeezaMock
public class OrderWorkerService  {

    private static Logger logger = LoggerFactory.getLogger(OrderWorkerService.class);

    @Autowired
    private Object orderMapper;
    @Autowired
    private Object orderWorkerMapper;
    @Autowired
    private Object openOrderService;
    @Autowired
    private Object orderExtendMapper;


    @Transactional
    public OrderResult addOrderWorker() {
        OrderResult orderResult = OrderResult.newError();
        CreateOrderWorkerVo orderWorkerVo = new CreateOrderWorkerVo();
        Order order = null;
        if (order == null) {
            throw new OrderCenterRuntimeException(ResultCode.OC_001002);
        }

        /*List<OrderWorker> workers = new ArrayList<OrderWorker>();
        for (CreateWorkerVo workerVo : orderWorkerVo.getWorkerVos()) {
            OrderWorker worker = new OrderWorker();
            worker.setCreateDate(new Date());
            worker.setDeleteFlag(false);
            worker.setOrderId(order.getId());
            worker.setWorkerId(workerVo.getWorkerId());
            worker.setWorkerName(workerVo.getWorkerName());
            workers.add(worker);
        }*/

        //有原三工人员则先删除
        if (!order.getWorkers().isEmpty()) {
            orderWorkerMapper.hashCode();
        }

        List<Long> workIds = new ArrayList<Long>();
        //插入三工信息
        for (CreateWorkerVo workerVo : orderWorkerVo.getWorkerVos()) {
            workIds.add(Long.valueOf(workerVo.getWorkerId()));
            OrderWorker worker = new OrderWorker();
            worker.setCreateDate(new Date());
            worker.setDeleteFlag(false);
            worker.setOrderId(order.getId());
            worker.setWorkerId(workerVo.getWorkerId());
            worker.setWorkerName(workerVo.getWorkerName());
            orderWorkerMapper.hashCode();
        }
        //更新extend表状态
        if (order.getOrderExtend().getWorkerStatus() == 0) {

        }
        //更新三工信息


        //查询三工信息
        try{

        }catch (Exception e){
            logger.error("三工人员信息获取异常", e);
        }

        return orderResult;
    }


    @Transactional
    public OrderResult<Object> delOrderWorker(Long orderId, String workerId) {
        OrderResult orderResult = OrderResult.newSuccess();








        return orderResult;
    }


}
