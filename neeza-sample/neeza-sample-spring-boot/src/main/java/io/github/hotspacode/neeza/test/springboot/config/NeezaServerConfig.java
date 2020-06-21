package io.github.hotspacode.neeza.test.springboot.config;

import io.github.hotspacode.neeza.base.util.NeezaConstant;
import io.github.hotspacode.neeza.spy.NeezaServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class NeezaServerConfig {
    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        String[] beans = applicationContext.getBeanDefinitionNames();

        Map<String, Object> serviceMap = new HashMap<>();
        if (null != beans && beans.length > 0) {
            for (String bean : beans) {
                serviceMap.put(bean, applicationContext.getBean(bean));
            }
        }

        NeezaServer.getInstance().registerPushServices(serviceMap).start("localhost",
                NeezaConstant.DEFAULT_SERVER_PORT,
                "io.github.hotspacode.neeza.test.springboot.service.impl"
                , "test1");
    }
}
