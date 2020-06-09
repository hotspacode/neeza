package io.github.hotspacode.neeza.test.springboot.service.impl;

import io.github.hotspacode.neeza.base.log.NeezaLog;
import org.springframework.stereotype.Service;

@Service
public class TestPushService {
    public void push1() {
        NeezaLog.info("TestPushService的push1被push执行成功");
    }

    public void push2(String name) {
        NeezaLog.info("TestPushService的push2被push执行成功{}", name);
    }
}
