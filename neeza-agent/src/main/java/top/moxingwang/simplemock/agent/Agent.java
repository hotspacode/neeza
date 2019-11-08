package top.moxingwang.simplemock.agent;


import java.io.File;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class Agent {
    static int aa = 1;

    static {
        aa = aa + 1;
    }

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println(" premain(String args, Instrumentation inst) ");
        inst.addTransformer(new AgentTransformer(0, null, null));
    }

    public static void agentmain(String args, Instrumentation inst) {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaa" + aa);
        {
            try {
                URL url = new URL("file:C:\\workspace\\simple-mock\\simple-mock-spy\\target\\simple-mock-spy-1.0.1-SNAPSHOT-jar-with-dependencies.jar");
                URLClassLoader urlClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
                Method add = URLClassLoader.class.getDeclaredMethod("addURL", new Class[]{URL.class});
                add.setAccessible(true);
                add.invoke(urlClassLoader, new Object[]{url});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


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
