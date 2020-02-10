package io.github.hotspacode.neeza.server.standalone.service;

import com.alibaba.fastjson.JSON;
import io.github.hotspacode.neeza.base.dto.MockData;
import io.github.hotspacode.neeza.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MockDataService {
    private static final Logger logger = LoggerFactory.getLogger(MockDataService.class);

    private static final String PREFIX_IP_PORT = "IPPORT:";
    private static final String PREFIX_TEMPLATE_DATA = "TEMPLATEDATA:";

    private static final Map<String, ConcurrentHashMap<String, Object>> cache = new ConcurrentHashMap<>();

    public void saveData(String methodDesc, MockData data, String ip, String port) {
        logger.info("save method desc {},{}", methodDesc, JSON.toJSONString(data));

        //todo 校验 daat

        boolean template = StringUtil.isBlank(ip);

        ConcurrentHashMap<String, Object> cacheMap = cache.get(methodDesc);

        if (null == cacheMap) {

            MockData cacheMockData = null;

            if (template) {

            }


            logger.info("no update {},{}", methodDesc, JSON.toJSONString(data));
            return;
        } else {
            cacheMap = new ConcurrentHashMap<>();
        }

        if (template) {
            cacheMap.put(PREFIX_TEMPLATE_DATA, data);
        } else {
            cacheMap.put(PREFIX_IP_PORT + ":" + ip + ":" + port, data);
        }

        cache.put(methodDesc, cacheMap);


    }

}
