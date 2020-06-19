package io.github.hotspacode.neeza.spy;

import java.lang.reflect.Method;

public class ClassReader {

    public static void readClass(Class clazz) {
        String className = clazz.getName();

        Method[] declaredMethods = clazz.getDeclaredMethods();
        if (null != declaredMethods && declaredMethods.length > 0) {

        }


        System.out.println(1);
    }

    public static void main(String[] args) {
        ClassReader.readClass(String.class);
    }

}
