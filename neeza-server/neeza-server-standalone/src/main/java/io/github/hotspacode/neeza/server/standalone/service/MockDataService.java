package io.github.hotspacode.neeza.server.standalone.service;

import com.alibaba.fastjson.JSON;
import io.github.hotspacode.neeza.base.dto.ServerNeezaClazz;
import io.github.hotspacode.neeza.base.log.NeezaLog;
import io.github.hotspacode.neeza.server.api.dto.MockCacheContainer;
import io.github.hotspacode.neeza.transport.api.TransportServerStatus;
import io.github.hotspacode.neeza.transport.client.http.TransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

@Service
public class MockDataService {
    private static final Logger logger = LoggerFactory.getLogger(MockDataService.class);

    int poolSize = Runtime.getRuntime().availableProcessors();
    BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(512);
    RejectedExecutionHandler policy = new ThreadPoolExecutor.DiscardPolicy();
    ExecutorService executorService = new ThreadPoolExecutor(poolSize, poolSize, 0, TimeUnit.SECONDS, queue, policy);
    private MockCacheContainer mockCacheContainer = new MockCacheContainer();

    @Autowired
    private TransportClient transportClient;

    public void heartbeat(String appName, String ip,
                          String port, Set<String> pulledMethods,
                          Set<ServerNeezaClazz> mockClasses) {
        mockCacheContainer.add(appName, ip, port, pulledMethods, mockClasses);
    }


    public void noticeClient(String methodDesc) {
        Set<String> noticingClient = new HashSet<>();
        Map<String, Set<String>> clientCaches = mockCacheContainer.getClientCaches();
        Set<Map.Entry<String, Set<String>>> entries = clientCaches.entrySet();
        for (Map.Entry<String, Set<String>> entry : entries) {
            if (entry.getValue().contains(methodDesc)) {
                noticingClient.add(entry.getKey());
            }
        }

        if (CollectionUtils.isEmpty(noticingClient)) {
            return;
        }

        NeezaLog.info("通知客户端方法mock发生变化{}", JSON.toJSONString(noticingClient));

        executorService.execute(() -> {
            for (String methodClient : noticingClient) {
                String[] split = methodClient.split(":");
                try {
                    dataChangeNotice(methodDesc, split[0], split[1]);
                } catch (Exception e) {
                    //todo 客户端异常处理流程
                    e.printStackTrace();
                }
            }
        });
    }

    private String dataChangeNotice(String methodDesc, String ip, String port) throws ExecutionException, InterruptedException {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("body", methodDesc);
        paramMap.put("clientPort", TransportServerStatus.getRealPort() + "");

        CompletableFuture<String> mockDataCompletableFuture = transportClient.execute(ip, Integer.valueOf(port), "neeza/spy/mock/data/change", paramMap, false)
                .thenApply(json -> {
                    return json;
                });
        String responseStr = mockDataCompletableFuture.get();
        return responseStr;
    }

}
