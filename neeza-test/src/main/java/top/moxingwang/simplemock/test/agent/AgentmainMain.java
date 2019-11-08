package top.moxingwang.simplemock.test.agent;

import top.moxingwang.simplemock.test.service.UserTest;

public class AgentmainMain {
    public static void main(String[] args) throws InterruptedException {
        for (; ; ) {
            new UserTest().test();
            Thread.sleep(5000);
        }

    }
}
