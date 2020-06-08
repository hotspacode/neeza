package io.github.hotspacode.neeza.server.standalone.controller;

import com.alibaba.fastjson.JSON;
import io.github.hotspacode.neeza.base.dto.MockData;
import io.github.hotspacode.neeza.core.cache.NeezaMockCache;
import io.github.hotspacode.neeza.server.standalone.service.ApiMockDataService;
import io.github.hotspacode.neeza.server.standalone.service.MockDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/mock")
public class MockController {

    private static final Logger logger = LoggerFactory.getLogger(MockController.class);

    @Autowired
    private MockDataService mockDataService;
    @Autowired
    private ApiMockDataService apiMockDataService;

    @GetMapping("/pull")
    public String pull(@RequestParam(value = "methodDesc") String methodDesc,
                       @RequestParam(value = "clientPort") Integer clientPort,
                       HttpServletRequest request) {
        String ip = getIpAddress(request);

        logger.info("NEEZA请求{}:{},{}", ip, clientPort, methodDesc);
        MockData mockDataDTO = apiMockDataService.getData(methodDesc);

        NeezaMockCache.cacheClientMethod(ip, clientPort, methodDesc);
        NeezaMockCache.cacheMethodClients(methodDesc,ip,clientPort);

        return JSON.toJSONString(mockDataDTO);
    }

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

}
