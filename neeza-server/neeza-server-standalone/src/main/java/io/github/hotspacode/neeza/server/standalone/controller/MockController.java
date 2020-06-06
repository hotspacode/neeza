package io.github.hotspacode.neeza.server.standalone.controller;

import com.alibaba.fastjson.JSON;
import io.github.hotspacode.neeza.base.dto.MockData;
import io.github.hotspacode.neeza.server.standalone.service.AliMockDataService;
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
    @Autowired
    private AliMockDataService aliMockDataService;

    @GetMapping("/pull")
    public String pull(@RequestParam(value = "methodDesc") String methodDesc,
                       @RequestParam(value = "clientPort") String clientPort,
                       HttpServletRequest request) {
        String ip = getIpAddress(request);

        logger.info("NEEZA请求{}:{},{}", ip, clientPort,methodDesc);

/*
        MockData mockDataDTO = new MockData();
        mockDataDTO.setType(MockData.Type.NONE);

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
        } else if (methodDesc.contains("order")) {
            mockDataDTO.setType(MockData.Type.ReturnBody);
            mockDataDTO.setBody("{\"@type\":\"io.github.hotspacode.neeza.test.springboot.pojo.Order\",\"id\":5438843,\"marketName\":\"service内部\",\"shopName\":\"shop内部name\"}");
        } else {
            mockDataDTO = mockDataService.pullData(methodDesc, ip, clientPort);
        }*/

        MockData mockDataDTO = aliMockDataService.getData(methodDesc);

        return JSON.toJSONString(mockDataDTO);
    }

    @GetMapping("/load")
    public String load(@RequestParam(value = "methodDesc") String methodDesc,
                       @RequestParam(value = "clientIp", required = false) String clientIp,
                       @RequestParam(value = "clientPort", required = false) String clientPort,
                       @RequestParam(value = "mockDataType") MockData.Type mockDataType,
                       @RequestParam(value = "mockDataBody") String mockDataBody) {
        MockData mockData = new MockData(mockDataType, mockDataBody);
        return mockDataService.load(methodDesc, mockData, clientIp, clientPort);
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
