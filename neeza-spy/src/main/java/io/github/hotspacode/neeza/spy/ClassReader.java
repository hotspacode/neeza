package io.github.hotspacode.neeza.spy;

import io.github.hotspacode.neeza.base.annotation.NeezaMock;
import io.github.hotspacode.neeza.core.domain.core.clazz.NeezaClazz;
import io.github.hotspacode.neeza.core.util.PackageUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

public final class ClassReader {

    private static Map<NeezaClazz, NeezaClazz> mockCache = null;

    private ClassReader() {
    }

    public static synchronized Set<NeezaClazz> getMockClasses() {
        if (null == mockCache) {
            mockCache = getMockClass();
        }

        return mockCache.keySet();
    }

    protected static synchronized Map<NeezaClazz, NeezaClazz> getMockClass() {
        Map<NeezaClazz, NeezaClazz> clazzNeezaClazzMap = new HashMap<>();

        List<String> classNames = PackageUtil.getClassName(NeezaServer.getPackageName(), true);
        if (null != classNames && classNames.size() > 0) {
            for (String className : classNames) {
                try {
                    NeezaClazz neezaClazz = readClass(Class.forName(className), false);
                    if (null != neezaClazz) {
                        clazzNeezaClazzMap.put(neezaClazz, neezaClazz);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        Map<String, Object> pushServiceMap = NeezaServer.getPushServiceMap();

        if (null != pushServiceMap && !pushServiceMap.isEmpty()) {
            for (Map.Entry<String, Object> stringObjectEntry : pushServiceMap.entrySet()) {
                try {
                    NeezaClazz neezaClazz = readClass(stringObjectEntry.getValue().getClass(), true);
                    NeezaClazz neezaClazzGenerated = clazzNeezaClazzMap.get(neezaClazz);

                    if (neezaClazzGenerated != null) {
                        neezaClazzGenerated.setServiceName(stringObjectEntry.getKey());
                        neezaClazzGenerated.setEnableMethodMockPush(true);
                    } else {
                        neezaClazz.setServiceName(stringObjectEntry.getKey());
                        clazzNeezaClazzMap.put(neezaClazz, neezaClazz);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


        return clazzNeezaClazzMap;
    }

    protected static NeezaClazz readClass(Class clazz, boolean isPushService) {
        NeezaClazz neezaClazz = null;

        if (isPushService) {
            neezaClazz = new NeezaClazz();
            neezaClazz.setEnableMethodMockPush(true);
        } else {
            Annotation[] annotations = clazz.getAnnotations();
            if (null != annotations && annotations.length > 0) {
                for (Annotation annotation : annotations) {
                    if (NeezaMock.class.getName().equals(annotation.annotationType().getName())) {
                        neezaClazz = new NeezaClazz();
                        neezaClazz.setEnableMethodMockPull(true);
                        break;
                    }
                }
            }

            if (null == neezaClazz) {
                return null;
            }
        }


        neezaClazz.setName(clazz.getName());
        neezaClazz.setGenericString(clazz.toGenericString());

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

}