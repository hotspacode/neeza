package io.github.hotspacode.neeza.server.standalone.controller;

import com.alibaba.fastjson.JSON;
import io.github.hotspacode.neeza.base.dto.MockData;
import io.github.hotspacode.neeza.server.standalone.service.MockDataService;
import io.github.hotspacode.neeza.server.standalone.service.StaticMockService;
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

    @GetMapping("/string")
    public String mock(@RequestParam(value = "methodDesc") String methodDesc,
                       @RequestParam(value = "clientPort") String clientPort,
                       HttpServletRequest request) {
        String ip = getIpAddress(request);

        logger.info("neeza method mock request {}:{}", ip, clientPort);


        MockData mockDataDTO = new MockData();
        mockDataDTO.setType(MockData.Type.NONE);

        System.out.println("mock服务被调用到" + methodDesc);

        if (methodDesc.contains("testMap")) {
            mockDataDTO.setType(MockData.Type.ReturnBody);
            mockDataDTO.setBody(StaticMockService.mapMock);
        } else if (methodDesc.contains("testString")) {
            mockDataDTO.setType(MockData.Type.ReturnBody);
            mockDataDTO.setBody(StaticMockService.mapMock);
        } else if (methodDesc.contains("testOrderResult")) {
            mockDataDTO.setType(MockData.Type.ReturnBody);
            mockDataDTO.setBody(StaticMockService.orderResultMock);
        } else if (methodDesc.contains("testVoid")) {
            mockDataDTO.setType(MockData.Type.VoidReturn);
        } else if (methodDesc.contains("testInt")) {
            mockDataDTO.setType(MockData.Type.ReturnBody);
            mockDataDTO.setBody(StaticMockService.intMock);
        } else if (methodDesc.contains("testInteger")) {
            mockDataDTO.setType(MockData.Type.ReturnBody);
            mockDataDTO.setBody(StaticMockService.intMock);
        } else if (methodDesc.contains("testDouble")) {
            mockDataDTO.setType(MockData.Type.ReturnBody);
            mockDataDTO.setBody(StaticMockService.doubleMock);
        } else if (methodDesc.contains("testArrayList")) {
            mockDataDTO.setType(MockData.Type.ReturnBody);
            mockDataDTO.setBody(StaticMockService.testArrayList);
        } else {
            mockDataDTO = mockDataService.pullData(methodDesc, ip, clientPort);
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
