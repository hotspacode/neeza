package io.github.hotspacode.neeza.test.springboot;

import io.github.hotspacode.neeza.base.util.NeezaConstant;
import io.github.hotspacode.neeza.spy.NeezaServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class Application {

	public static void main(String[] args) {
		NeezaServer.start("localhost", NeezaConstant.DEFAULT_SERVER_PORT,"io/github/hotspacode/neeza/test");
		SpringApplication.run(Application.class, args);
	}

}
