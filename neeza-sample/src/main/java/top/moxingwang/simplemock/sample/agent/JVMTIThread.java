package top.moxingwang.simplemock.sample.agent;

import com.sun.tools.attach.*;
import top.moxingwang.simplemock.sample.service.UserTest;

import java.io.IOException;
import java.util.List;

public class JVMTIThread {

    public static void main(String[] args)
            throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        for (VirtualMachineDescriptor vmd : list) {
            if (vmd.displayName().endsWith("Application")) {
                VirtualMachine virtualMachine = VirtualMachine.attach(vmd.id());
                virtualMachine.loadAgent("C:\\workspace\\neeza\\neeza-agent\\target\\neeza-agent-1.0.1-SNAPSHOT-jar-with-dependencies.jar",
                        "com.chinaredstar.ordercenter.service.ali.AliRefundOrderService");
                System.out.println("ok");
                virtualMachine.detach();
            }
        }
    }
}
