package io.github.hotspacode.neeza.client.common;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MethodPropertyTest {
    public static void main(String[] args) {
        Class testClass = MethodPropertyTest.class;

        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        for (Method declaredMethod : testClass.getDeclaredMethods()) {
            System.out.println("className: "+testClass.getName());

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
    public List<List<String>> getListStr(String aa, ArrayList<List<Object>> bb) {

        return null;
    }
}
