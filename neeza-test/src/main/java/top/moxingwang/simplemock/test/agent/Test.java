
package top.moxingwang.simplemock.test.agent;


import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import top.moxingwang.simplemock.test.service.UserTest;
import top.moxingwang.simplemock.core.util.JUtil;

import java.io.File;
import java.io.IOException;

public final class Test {
    public static void main(String[] args) {
        boolean flag = false;
        while (true) {


            try {
                UserTest userTest = new UserTest();
                userTest.test();

                Thread.sleep(1000);

                if(!flag){
                    // Attach到被监控的JVM进程上
                    VirtualMachine virtualmachine = null;
                    try {
                        virtualmachine = VirtualMachine.attach(JUtil.getPid());

                        // 让JVM加载jmx Agent
                        String jmxAgent = "C:\\workspace\\simple-mock\\simple-mock-agent\\target\\simple-mock-agent-1.0.1-SNAPSHOT.jar";
                        String home = virtualmachine.getSystemProperties().getProperty("java.home");
                        String agent = home + File.separator + "lib" + File.separator
                                + "management-agent.jar";

                        virtualmachine.loadAgent(jmxAgent);
                        virtualmachine.detach();
                    } catch (AttachNotSupportedException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (AgentLoadException e) {
                        e.printStackTrace();
                    } catch (AgentInitializationException e) {
                        e.printStackTrace();
                    }

                    flag = true;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
