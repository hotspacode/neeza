package io.github.hotspacode.neeza.server.standalone.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ClientManagementService {
    private static final Logger logger = LoggerFactory.getLogger(ClientManagementService.class);

    private static final Map<String, Integer> cache = new ConcurrentHashMap<>();

    public void loadClient(String ip, Integer port) {
        Integer cachePort = cache.get(ip);
        if (Objects.equals(port, cachePort)) {
            return;
        }

        cache.put(ip, port);
    }

    public void methodDescDataChangeNotice(){

    }
}
