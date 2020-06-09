package io.github.hotspacode.neeza.server.standalone.config;

import io.github.hotspacode.neeza.base.api.INeezaMockPushSpyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class NeezaMockPushSpyService implements INeezaMockPushSpyService {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public Object getService(String serviceName) {
        return applicationContext.getBean(serviceName);
    }
}
