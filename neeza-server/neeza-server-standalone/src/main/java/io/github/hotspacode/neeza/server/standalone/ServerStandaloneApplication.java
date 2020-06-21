package io.github.hotspacode.neeza.server.standalone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableJpaRepositories(basePackages = "io.github.hotspacode.neeza.server.standalone.dao")
@EntityScan(basePackages = "io.github.hotspacode.neeza.server.standalone.model")
public class ServerStandaloneApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerStandaloneApplication.class, args);
    }

}
