package top.moxingwang.simplemock.sample.agent;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class LoaderTest {
    public static void main(String[] args) throws NoSuchMethodException, MalformedURLException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {
        {
            URL url = new URL("file:C:\\workspace\\simple-mock\\simple-mock-agent\\target\\simple-mock-agent-1.0.1-SNAPSHOT-jar-with-dependencies.jar");
            URLClassLoader urlClassLoader= (URLClassLoader) ClassLoader.getSystemClassLoader();
            Method add = URLClassLoader.class.getDeclaredMethod("addURL", new Class[] { URL.class });
            add.setAccessible(true);
            add.invoke(urlClassLoader, new Object[] {url });
            Class c=Class.forName("top.moxingwang.simplemock.agent.Agent");
        }

        {
            URL url = new URL("file:C:\\workspace\\simple-mock\\simple-mock-spy\\target\\simple-mock-spy-1.0.1-SNAPSHOT-jar-with-dependencies.jar");
            URLClassLoader urlClassLoader= (URLClassLoader) ClassLoader.getSystemClassLoader();
            Method add = URLClassLoader.class.getDeclaredMethod("addURL", new Class[] { URL.class });
            add.setAccessible(true);
            add.invoke(urlClassLoader, new Object[] {url });
            Class c=Class.forName("top.moxingwang.simplemock.spy.SpringAutoConfiguration");
        }



    }


}
