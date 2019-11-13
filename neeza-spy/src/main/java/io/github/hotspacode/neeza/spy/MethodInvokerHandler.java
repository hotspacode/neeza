package io.github.hotspacode.neeza.spy;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class MethodInvokerHandler {

    public static final int MAX_CONTAINER_SIZE = 1000;
    public static final int UNLOAD_SIZE = 50;

    private static ArrayBlockingQueue invokeQueue = null;
    private static ConcurrentHashMap mapContainer = null;

    private MethodInvokerHandler() {
    }

    static synchronized void init() {
        if (Objects.nonNull(invokeQueue)) {
            invokeQueue = new ArrayBlockingQueue(MAX_CONTAINER_SIZE);

            mapContainer = new ConcurrentHashMap();

            Thread taskThread = new Thread(new InvokeTask());
            taskThread.start();
        }
    }

    static boolean offer(Method targetMethod, List<Object> localVariable) {
        if (Objects.isNull(invokeQueue) || Objects.isNull(targetMethod)) {
            return false;
        }


        return false;
    }

    static class InvokeTask implements Runnable {

        @Override
        public void run() {
            if (mapContainer.size() >= MAX_CONTAINER_SIZE) {
                //卸载掉部分数据
                int count = 0;
                for (Object o : mapContainer.keySet()) {
                    if (count >= UNLOAD_SIZE) {
                        break;
                    }
                    mapContainer.remove(o);
                    count++;
                }
            }


        }
    }

}
