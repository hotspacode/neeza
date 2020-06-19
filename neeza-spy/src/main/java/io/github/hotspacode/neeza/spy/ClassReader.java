package io.github.hotspacode.neeza.spy;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class ClassReader {

    public static void readMethod(Class service){
        Method[] declaredMethods = service.getDeclaredMethods();
        if (null != declaredMethods && declaredMethods.length > 0) {
            for (Method declaredMethod : declaredMethods) {
                //解析返回对象

                //解析参数列表对象

            }
        }

    }

    public static void readClass(Class clazz)  {
        String className = clazz.getName();

        try {
            Method[] declaredMethods = clazz.getDeclaredMethods();
            if (null != declaredMethods && declaredMethods.length > 0) {
                for (Method declaredMethod : declaredMethods) {
                    String name = declaredMethod.getName();
                    Class<?> returnType = declaredMethod.getReturnType();

                    //参数对象
                    {
                        Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
                        if (parameterTypes != null && parameterTypes.length > 0) {
                            for (Class<?> parameterType : parameterTypes) {

                            }
                        }

                    }


                    //返回对象
                    {
                        Type genericReturnType = declaredMethod.getGenericReturnType();
                        if (genericReturnType instanceof ParameterizedTypeImpl) {
                            ParameterizedTypeImpl typeImpl = (ParameterizedTypeImpl) genericReturnType;
                            Type[] actualTypeArguments = typeImpl.getActualTypeArguments();
                            Class<?> rawType = typeImpl.getRawType();
                            Type ownerType = typeImpl.getOwnerType();

                            Class<? extends Type> aClass = genericReturnType.getClass();

                            System.out.println(1);
                        }
                    }



                    System.out.println(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println(1);
    }

    public static void main(String[] args) {
        ClassReader.readClass(A.class);
    }

}

class A<T> {
    public void t1(String a, String[] b) {

    }

    public Map<String, Object> t2(List<String> a) {
        return null;
    }

    public T t3(){
        return null;
    }
}