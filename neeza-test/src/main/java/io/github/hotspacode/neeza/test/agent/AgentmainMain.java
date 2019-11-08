package io.github.hotspacode.neeza.test.agent;

import io.github.hotspacode.neeza.test.service.UserTest;

public class AgentmainMain {
    public static void main(String[] args) throws InterruptedException {
        for (; ; ) {
            new UserTest().test();
            Thread.sleep(5000);
        }

    }
}
