package io.github.hotspacode.neeza.test.springboot.config;

import io.github.hotspacode.neeza.base.util.NeezaConstant;
import io.github.hotspacode.neeza.spy.NeezaServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class NeezaServerConfig {
    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        NeezaServer.getInstance().start(
                "localhost",
                NeezaConstant.DEFAULT_SERVER_PORT,
                "io.github.hotspacode.neeza.test.springboot.service")
                .registerPush((String methodDesc) -> {
                    return applicationContext.getBean(methodDesc);
                });
    }
}
