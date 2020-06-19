package io.github.hotspacode.neeza.spy;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class ClassReader {

    public static void readClass(Class clazz) {
        String className = clazz.getName();

        Method[] declaredMethods = clazz.getDeclaredMethods();
        if (null != declaredMethods && declaredMethods.length > 0) {
            for (Method declaredMethod : declaredMethods) {
                String name = declaredMethod.getName();
                Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
                Class<?> returnType = declaredMethod.getReturnType();
                Type genericReturnType = declaredMethod.getGenericReturnType();
                Class<? extends Type> aClass = genericReturnType.getClass();
                System.out.println(genericReturnType);
                System.out.println(1);
            }
        }


        System.out.println(1);
    }

    public static void main(String[] args) {
        ClassReader.readClass(A.class);
    }

}

class A {
    public void t1(String a, String[] b) {

    }

    public Map<String, Object> t2(List<String> a) {
        return null;
    }
}