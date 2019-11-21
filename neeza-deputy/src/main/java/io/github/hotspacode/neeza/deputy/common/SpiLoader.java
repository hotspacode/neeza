package io.github.hotspacode.neeza.deputy.common;

import io.github.hotspacode.neeza.deputy.annotation.SpiOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

public final class SpiLoader {

    private static final Map<String, ServiceLoader> SERVICE_LOADER_MAP = new ConcurrentHashMap<String, ServiceLoader>();

    public static <T> T loadHighestPriorityInstance(Class<T> clazz) {
        try {
            String key = clazz.getName();
            ServiceLoader<T> serviceLoader = SERVICE_LOADER_MAP.get(key);
            if (serviceLoader == null) {
                serviceLoader = ServiceLoader.load(clazz, clazz.getClassLoader());
                SERVICE_LOADER_MAP.put(key, serviceLoader);
            }

            if (serviceLoader == null) {
                return null;
            }

            SpiOrderWrapper<T> w = null;
            for (T spi : serviceLoader) {
                int order = SpiOrderResolver.resolveOrder(spi);
                if (w == null || order < w.order) {
                    w = new SpiOrderWrapper<>(order, spi);
                }
            }
            return w == null ? null : w.spi;
        } catch (Throwable t) {
            t.printStackTrace();
            return null;
        }
    }

    public static <T> List<T> loadInstanceList(Class<T> clazz) {
        try {
            String key = clazz.getName();
            ServiceLoader<T> serviceLoader = SERVICE_LOADER_MAP.get(key);
            if (serviceLoader == null) {
                serviceLoader = ServiceLoader.load(clazz, clazz.getClassLoader());
                SERVICE_LOADER_MAP.put(key, serviceLoader);
            }

            List<T> list = new ArrayList<>();
            for (T spi : serviceLoader) {
                list.add(spi);
            }
            return list;
        } catch (Throwable t) {
            t.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static class SpiOrderResolver {
        private static <T> int resolveOrder(T spi) {
            if (!spi.getClass().isAnnotationPresent(SpiOrder.class)) {
                return SpiOrder.LOWEST_PRECEDENCE;
            } else {
                return spi.getClass().getAnnotation(SpiOrder.class).value();
            }
        }
    }

    private static class SpiOrderWrapper<T> {
        private final int order;
        private final T spi;

        SpiOrderWrapper(int order, T spi) {
            this.order = order;
            this.spi = spi;
        }

        int getOrder() {
            return order;
        }

        T getSpi() {
            return spi;
        }
    }

    private SpiLoader() {
    }
}
