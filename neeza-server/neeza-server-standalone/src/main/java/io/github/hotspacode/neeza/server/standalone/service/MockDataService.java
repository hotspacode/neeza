package io.github.hotspacode.neeza.server.standalone.service;

import com.alibaba.fastjson.JSON;
import io.github.hotspacode.neeza.base.dto.MockData;
import io.github.hotspacode.neeza.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MockDataService {
    private static final Logger logger = LoggerFactory.getLogger(MockDataService.class);

    private static final String PREFIX_IP_PORT = "IPPORT:";
    private static final String PREFIX_TEMPLATE_DATA = "TEMPLATEDATA:";

    private static final Map<String, ConcurrentHashMap<String, Object>> cache = new ConcurrentHashMap<>();

    public String load(String methodDesc, MockData data, String ip, String port) {
        logger.info("save method desc {},{}", methodDesc, JSON.toJSONString(data));

        //todo 校验 daat

        boolean template = StringUtil.isBlank(ip);

        ConcurrentHashMap<String, Object> cacheMap = cache.get(methodDesc);

        if (null == cacheMap) {
            MockData cacheMockData = null;

            if (template) {
                cacheMockData = (MockData) cacheMap.get(PREFIX_TEMPLATE_DATA);
            } else {
                cacheMockData = (MockData) cacheMap.get(PREFIX_IP_PORT + ip + ":" + port);
            }

            if (Objects.equals(data.getBody(), cacheMockData.getBody())) {
                logger.info("no update {},{}", methodDesc, JSON.toJSONString(data));
                return "no update";
            }
        } else {
            cacheMap = new ConcurrentHashMap<>();
        }

        if (template) {
            cacheMap.put(PREFIX_TEMPLATE_DATA, data);
        } else {
            cacheMap.put(PREFIX_IP_PORT + ":" + ip + ":" + port, data);
        }

        cache.put(methodDesc, cacheMap);

        // notice
        for (Map.Entry<String, Object> stringObjectEntry : cacheMap.entrySet()) {
            if (template) {
                if (stringObjectEntry.getValue().equals(PREFIX_IP_PORT + ":" + ip + ":" + port)) {
                    dataChangeNotice(methodDesc, ip, port);
                    break;
                }
            } else {
                dataChangeNotice(methodDesc, ip, port);
            }
        }

        return "OK";
    }

    public MockData pullData(String methodDesc, String ip, String port) {
        MockData mockData = null;

        ConcurrentHashMap<String, Object> dataMap = cache.get(methodDesc);
        if (null == dataMap) {
            dataMap = new ConcurrentHashMap<>();
            cache.put(methodDesc, dataMap);
        }

        mockData = (MockData) dataMap.get(PREFIX_IP_PORT + ip + ":" + port);
        if (null == mockData) {
            mockData = (MockData) dataMap.get(PREFIX_TEMPLATE_DATA);
            dataMap.put(PREFIX_IP_PORT + ip + ":" + port, dataMap);
        }

        return mockData;
    }

    private void dataChangeNotice(String methodDesc, String ip, String port) {
        try {

        } catch (Exception e) {

        }
    }

}
