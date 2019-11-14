package io.github.hotspacode.neeza.client.common;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MethodPropertyTest {
    public static void main(String[] args) {
        Class testClass = MethodPropertyTest.class;
        try {
            Method getStaticListStr = testClass.getMethod("getStaticListStr", String.class, ArrayList.class);
            System.out.println(getStaticListStr.toGenericString());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        for (Method declaredMethod : testClass.getDeclaredMethods()) {
            System.out.println("className: "+testClass.getName());
            Parameter[] parameters = declaredMethod.getParameters();
            Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
            Type[] genericParameterTypes = declaredMethod.getGenericParameterTypes();
            System.out.println(declaredMethod.toString());
            System.out.println(declaredMethod.toGenericString());
            System.out.println(declaredMethod.getGenericReturnType().toString());
        }
    }

    public List<List<String>> getListStr(String aa, String bb) {

        return null;
    }
    public List<List<String>> getListStr(String aa, List bb) {

        return null;
    }
    public static List<List<String>> getStaticListStr(String aa, ArrayList<Set<Object>> bb) {

        return null;
    }

    public List<List<String>> getListStr(String aa, ArrayList<List<Object>> bb) {

        return null;
    }


    public int getInt(String aa, List<Set<Object>> b) {


        System.out.println("方法未被mock");
        return 1;
    }

}
