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

    private static final Map<String, MockData> cache = new ConcurrentHashMap<>();

    public void saveData(String methodDesc, MockData data) {
        logger.info("save method desc {},{}", methodDesc, JSON.toJSONString(data));

        //todo 校验 daat

        MockData cacheData = cache.get(methodDesc);

        if (null != cacheData && Objects.equals(cacheData.getBody(), data.getBody())) {
            logger.info("no update {},{}", methodDesc, JSON.toJSONString(data));
            return;
        }


        cache.put(methodDesc, data);


    }
}
