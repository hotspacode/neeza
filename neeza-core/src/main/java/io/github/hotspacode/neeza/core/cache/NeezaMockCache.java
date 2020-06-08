package io.github.hotspacode.neeza.core.cache;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class NeezaMockCache {
    //ip:port->methods
    private static final ConcurrentHashMap<String, Set<String>> clientMethodCache = new ConcurrentHashMap<>();
    //method->ip:ports
    private static final ConcurrentHashMap<String, Set<String>> methodClientCache = new ConcurrentHashMap<>();

    public static void cacheClientMethod(String ip, Integer port, String methodDesc) {
        String key = ip + ":" + port;
        Set<String> methods = clientMethodCache.get(key);
        if (null == methods) {
            methods = new HashSet<>();
        }
        methods.add(methodDesc);
        clientMethodCache.put(key, methods);
    }

    public static Set<String> getClientMethods(String ip, Integer port) {
        String key = ip + ":" + port;
        Set<String> methods = clientMethodCache.get(key);

        return methods;
    }

    public static void cacheMethodClients(String methodDesc, String ip, Integer port) {
        String client = ip + ":" + port;
        Set<String> clients = methodClientCache.get(methodDesc);
        if (null == clients) {
            clients = new HashSet<>();
        }
        clients.add(client);
        methodClientCache.put(methodDesc, clients);
    }

    public static Set<String> getMethodClients(String methodDesc) {
        Set<String> clients = methodClientCache.get(methodDesc);

        return clients;
    }

}
