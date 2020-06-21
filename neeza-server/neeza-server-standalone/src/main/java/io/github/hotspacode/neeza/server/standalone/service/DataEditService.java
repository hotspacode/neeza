package io.github.hotspacode.neeza.server.standalone.service;

import com.alibaba.fastjson.JSON;
import io.github.hotspacode.neeza.base.dto.PushTransportData;
import io.github.hotspacode.neeza.base.log.NeezaLog;
import io.github.hotspacode.neeza.server.standalone.dao.ApiMockDataRepository;
import io.github.hotspacode.neeza.server.standalone.model.ApiMockData;
import io.github.hotspacode.neeza.transport.client.http.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class DataEditService {
    @Autowired
    private ApiMockDataRepository apiMockDataRepository;
    @Autowired
    private TransportClient transportClient;
    @Autowired
    private MockDataService mockDataService;

    public void publish(String appName,String methodDesc, String content, Integer methodType) {
        NeezaLog.info("发布方法{},{}",appName, methodDesc);
        ApiMockData apiMockDataExample = new ApiMockData();
        apiMockDataExample.setApp_id(appName);
        apiMockDataExample.setApi_name(methodDesc);
        Example<ApiMockData> example = Example.of(apiMockDataExample);

        ApiMockData apiMockData = null;
        Optional<ApiMockData> one = apiMockDataRepository.findOne(example);
        if (one.isPresent()) {
            apiMockData = one.get();
            if (content.equals(apiMockData.getApi_data())) {
                return;
            } else {
                apiMockData.setApi_data(content);
            }
        } else {
            apiMockData = new ApiMockData();
            apiMockData.setApp_id(appName);
            apiMockData.setApi_name(methodDesc);
            apiMockData.setApi_data(content);
            apiMockData.setMethod_type(methodType);
        }
        //落库
        if (null == apiMockData.getId()) {
            apiMockDataRepository.save(apiMockData);
        } else {
            apiMockDataRepository.updateContentById(apiMockData.getId(), apiMockData.getApi_data());
        }

        //通知应用
        mockDataService.noticeClient(methodDesc);
    }

    public String push(PushTransportData pushTransportData, String ip, String port) {
        NeezaLog.info("push操作{},{},{}", ip, port, pushTransportData);
        return pushService(pushTransportData, ip, port);
    }

    private String pushService(PushTransportData pushTransportData, String ip, String port) {
        try {
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("body", JSON.toJSONString(pushTransportData));

            CompletableFuture<String> mockDataCompletableFuture = transportClient.execute(ip, Integer.valueOf(port), "neeza/spy/mock/data/push", paramMap, false)
                    .thenApply(json -> {
                        return json;
                    });
            String responseStr = mockDataCompletableFuture.get();

            return responseStr;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
