package io.github.hotspacode.neeza.server.standalone.controller;

import com.alibaba.fastjson.JSON;
import io.github.hotspacode.neeza.base.dto.MockData;
import io.github.hotspacode.neeza.base.log.NeezaLog;
import io.github.hotspacode.neeza.server.standalone.service.StaticMockService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mock")
public class MockController {

    @GetMapping("/string")
    public String mock(@RequestParam(value = "methodName") String methodName,
                       @RequestParam(value = "clientPort") String clientPort,
                       ServerHttpRequest request) {
        String ip = getIpAddress(request);

        NeezaLog.info("neeza method mock request s%:s%", ip, clientPort);

        MockData mockDataDTO = new MockData();
        mockDataDTO.setType(MockData.Type.NONE);

        System.out.println("mock服务被调用到" + methodName);

        if (methodName.contains("testMap")) {
            mockDataDTO.setType(MockData.Type.ReturnBody);
            mockDataDTO.setBody(StaticMockService.mapMock);
        } else if (methodName.contains("testString")) {
            mockDataDTO.setType(MockData.Type.ReturnBody);
            mockDataDTO.setBody(StaticMockService.mapMock);
        } else if (methodName.contains("testOrderResult")) {
            mockDataDTO.setType(MockData.Type.ReturnBody);
            mockDataDTO.setBody(StaticMockService.orderResultMock);
        } else if (methodName.contains("testVoid")) {
            mockDataDTO.setType(MockData.Type.VoidReturn);
        } else if (methodName.contains("testInt")) {
            mockDataDTO.setType(MockData.Type.ReturnBody);
            mockDataDTO.setBody(StaticMockService.intMock);
        } else if (methodName.contains("testInteger")) {
            mockDataDTO.setType(MockData.Type.ReturnBody);
            mockDataDTO.setBody(StaticMockService.intMock);
        } else if (methodName.contains("testDouble")) {
            mockDataDTO.setType(MockData.Type.ReturnBody);
            mockDataDTO.setBody(StaticMockService.doubleMock);
        } else if (methodName.contains("testArrayList")) {
            mockDataDTO.setType(MockData.Type.ReturnBody);
            mockDataDTO.setBody(StaticMockService.testArrayList);
        } else {
            mockDataDTO.setType(MockData.Type.ReturnBody);
            mockDataDTO.setBody(StaticMockService.mapMock);
        }


        return JSON.toJSONString(mockDataDTO);
    }


    public static String getIpAddress(ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        String ip = headers.getFirst("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.indexOf(",") != -1) {
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddress().getAddress().getHostAddress();
        }
        return ip;
    }

}
