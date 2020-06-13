package io.github.hotspacode.neeza.server.standalone.service;

import com.alibaba.fastjson.JSON;
import io.github.hotspacode.neeza.base.log.NeezaLog;
import io.github.hotspacode.neeza.server.api.cache.NeezaMockServerCache;
import io.github.hotspacode.neeza.transport.api.TransportServerStatus;
import io.github.hotspacode.neeza.transport.client.http.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

@Service
public class NoticeService {
    @Autowired
    private TransportClient transportClient;

    int poolSize = Runtime.getRuntime().availableProcessors();
    BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(512);
    RejectedExecutionHandler policy = new ThreadPoolExecutor.DiscardPolicy();
    ExecutorService executorService = new ThreadPoolExecutor(poolSize, poolSize, 0, TimeUnit.SECONDS, queue, policy);

    public void noticeClient(String methodDesc) {
        Set<String> methodClients = NeezaMockServerCache.getMethodClients(methodDesc);
        if (CollectionUtils.isEmpty(methodClients)) {
            return;
        }

        NeezaLog.info("通知客户端方法mock发生变化{}", JSON.toJSONString(methodClients));

        executorService.execute(() -> {
            for (String methodClient : methodClients) {
                String[] split = methodClient.split(":");
                try {
                    dataChangeNotice(methodDesc, split[0], split[1]);
                } catch (Exception e) {
                    //todo 客户端异常处理流程
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
