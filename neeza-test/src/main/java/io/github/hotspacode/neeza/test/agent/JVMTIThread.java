package io.github.hotspacode.neeza.test.agent;

import com.sun.tools.attach.*;
import io.github.hotspacode.neeza.test.service.UserTest;

import java.io.IOException;
import java.util.List;

public class JVMTIThread {

    public static void main(String[] args)
            throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        for (VirtualMachineDescriptor vmd : list) {
            if (vmd.displayName().endsWith("AgentmainMain")) {
                VirtualMachine virtualMachine = VirtualMachine.attach(vmd.id());
                virtualMachine.loadAgent("C:\\workspace\\neeza\\neeza-agent\\target\\neeza-agent-1.0.1-SNAPSHOT.jar", UserTest.class.getName());
                System.out.println("ok");
                virtualMachine.detach();
            }
        }
    }
}
