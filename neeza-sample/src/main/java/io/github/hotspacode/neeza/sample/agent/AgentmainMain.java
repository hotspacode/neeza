package io.github.hotspacode.neeza.sample.agent;


import io.github.hotspacode.neeza.sample.service.UserTest;

public class AgentmainMain {
    public static void main(String[] args) throws InterruptedException {
        for (; ; ) {
            new UserTest().test();
            Thread.sleep(5000);
        }


    }
}
