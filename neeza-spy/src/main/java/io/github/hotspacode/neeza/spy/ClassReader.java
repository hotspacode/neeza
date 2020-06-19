package io.github.hotspacode.neeza.spy;

import io.github.hotspacode.neeza.core.domain.core.Entity;
import io.github.hotspacode.neeza.core.domain.core.clazz.NeezaClazz;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClassReader {

    public static void readMethod(Class service) {
        Method[] declaredMethods = service.getDeclaredMethods();
        if (null != declaredMethods && declaredMethods.length > 0) {
            for (Method declaredMethod : declaredMethods) {
                //解析返回对象
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

                //解析参数列表对象

            }
        }

    }

    public static NeezaClazz readClass(Class clazz) {
        NeezaClazz neezaClazz = new NeezaClazz();
        neezaClazz.setType(clazz.getTypeName());

        Field[] declaredFields = clazz.getDeclaredFields();
        if (null == declaredFields || declaredFields.length == 0) {
            return neezaClazz;
        }

        List<NeezaClazz> filds = new ArrayList<>();
        for (Field declaredField : declaredFields) {
            if (declaredField.getClass().isPrimitive()) {
                NeezaClazz clazzFild = new NeezaClazz();
                clazzFild.setType(declaredField.getClass().getTypeName());
                filds.add(clazzFild);
            }else {
                filds.add(readClass(declaredField.getClass()));
            }
        }
        neezaClazz.setFields(filds);


        System.out.println(1);
        return neezaClazz;
    }

    public static void main(String[] args) {
        ClassReader.readClass(A.class);
    }

}

class A<T> {
    private String a;
    public void t1(String a, String[] b) {

    }

    public Map<String, Object> t2(List<String> a) {
        return null;
    }

    public T t3() {
        return null;
    }
}