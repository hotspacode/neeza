package io.github.hotspacode.neeza.server.standalone.controller;

import com.alibaba.fastjson.JSON;
import io.github.hotspacode.neeza.base.dto.MockData;
import io.github.hotspacode.neeza.base.log.NeezaLog;
import io.github.hotspacode.neeza.server.standalone.service.StaticMockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/mock")
public class MockController {

    @GetMapping("/string")
    public String mock(@RequestParam(value = "methodName") String methodName,
                       @RequestParam(value = "clientPort") String clientPort,
                       HttpServletRequest request) {
        String ip = getIpAddress(request);

        NeezaLog.info("neeza method mock request {0}:{1}", ip, clientPort);

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
