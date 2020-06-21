package io.github.hotspacode.neeza.server.api.dto;

import io.github.hotspacode.neeza.base.dto.NeezaClazz;
import io.github.hotspacode.neeza.base.dto.ServerNeezaClazz;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MockCacheContainer extends ConcurrentHashMap<String, Set<ServerNeezaClazz>> {
    private Map<String,Set<String>> clientCaches = new HashMap<>();


    public synchronized Set<ServerNeezaClazz> add(String key, String ip,
                                                  String port, Set<String> pulledMethods,
                                                  Set<ServerNeezaClazz> mockClasses) {
        Set<ServerNeezaClazz> clazzes = get(key);
        if (null == clazzes) {
            clazzes = new HashSet<>();
            put(key, clazzes);
        }
        if (mockClasses != null && mockClasses.size()>0) {
            for (ServerNeezaClazz mockClass : mockClasses) {
                if (!clazzes.contains(mockClass)) {
                    clazzes.add(mockClass);
                }
            }
        }

        clientCaches.put(ip + ":" + port, pulledMethods);

        return clazzes;
    }

    @Override
    public Set<ServerNeezaClazz> get(Object key) {
        return super.get(key);
    }

    public Map<String, Set<String>> getClientCaches() {
        return clientCaches;
    }
}
