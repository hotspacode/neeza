package io.github.hotspacode.neeza.spy;

import io.github.hotspacode.neeza.base.annotation.NeezaMock;
import io.github.hotspacode.neeza.core.domain.core.clazz.NeezaClazz;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClassReader {

    public static NeezaClazz readClass(Class clazz) {
        NeezaClazz neezaClazz = new NeezaClazz();
        neezaClazz.setName(clazz.getName());
        neezaClazz.setGenericString(clazz.toGenericString());

        Annotation[] annotations = clazz.getAnnotations();
        if (null != annotations && annotations.length > 0) {
            for (Annotation annotation : annotations) {
                if (NeezaMock.class.getName().equals(annotation.annotationType().getName())) {
                    neezaClazz.setEnableMethodMockPull(true);
                    break;
                }
            }
        }
        Method[] declaredMethods = clazz.getDeclaredMethods();
        if (null != declaredMethods && declaredMethods.length > 0) {
            List<NeezaClazz.NeezaMethod> methods = new ArrayList<>();
            for (Method declaredMethod : declaredMethods) {
                NeezaClazz.NeezaMethod method = new NeezaClazz.NeezaMethod();
                method.setName(declaredMethod.getName());
                method.setGenericString(declaredMethod.toGenericString());
                methods.add(method);
            }
            neezaClazz.setMethods(methods);
        }

        return neezaClazz;
    }

    public static void main(String[] args) {
        ClassReader.readClass(A.class);
    }

}

@NeezaMock
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