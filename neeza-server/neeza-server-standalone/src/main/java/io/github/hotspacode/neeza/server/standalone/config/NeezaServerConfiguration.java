package io.github.hotspacode.neeza.server.standalone.config;

import io.github.hotspacode.neeza.transport.client.http.TransportClient;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class NeezaServerConfiguration {

    @Bean(name = "transportClient")
    public TransportClient transportClient() {
        return new TransportClient();
    }

}
