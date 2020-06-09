package io.github.hotspacode.neeza.server.standalone.service;

import io.github.hotspacode.neeza.base.log.NeezaLog;
import org.springframework.stereotype.Service;

@Service
public class TestPushService {
    public void push1() {
        NeezaLog.info("push执行成功");
    }

    public void push2(String name) {
        NeezaLog.info("push执行成功{}", name);
    }
}
