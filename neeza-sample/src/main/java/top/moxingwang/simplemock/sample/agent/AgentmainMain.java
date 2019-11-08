package top.moxingwang.simplemock.sample.agent;


import top.moxingwang.simplemock.sample.service.UserTest;

public class AgentmainMain {
    public static void main(String[] args) throws InterruptedException {
        for (; ; ) {
            new UserTest().test();
            Thread.sleep(5000);
        }


    }
}
