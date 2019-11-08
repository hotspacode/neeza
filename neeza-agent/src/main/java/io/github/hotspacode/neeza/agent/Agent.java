package io.github.hotspacode.neeza.agent;


import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

public class Agent {

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println(" premain(String args, Instrumentation inst) ");
        inst.addTransformer(new AgentTransformer(0, null, null));
    }

    public static void agentmain(String args, Instrumentation inst) {

        System.out.println(" agentmain(String args, Instrumentation inst) ");
        inst.addTransformer(new AgentTransformer(1, args, null), true);
        try {
            inst.retransformClasses(Class.forName(args));
        } catch (UnmodifiableClassException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
