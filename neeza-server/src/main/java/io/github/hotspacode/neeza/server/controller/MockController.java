package io.github.hotspacode.neeza.server.controller;

import com.alibaba.fastjson.JSON;
import io.github.hotspacode.neeza.deputy.dto.MockData;
import io.github.hotspacode.neeza.server.service.StaticMockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mock")
public class MockController {

    @GetMapping("/string/{methodName}")
    public String mock(@PathVariable(value = "methodName") String methodName) {
        MockData mockDataDTO = new MockData();
        mockDataDTO.setType(MockData.Type.NONE);

        System.out.println("mock服务被调用到" + methodName);

        if (methodName.endsWith("testMap")) {
            mockDataDTO.setType(MockData.Type.ReturnBody);
            mockDataDTO.setBody(StaticMockService.mapMock);
        } else if (methodName.endsWith("testString")) {
            mockDataDTO.setType(MockData.Type.ReturnBody);
            mockDataDTO.setBody(StaticMockService.mapMock);
        } else if (methodName.endsWith("testOrderResult")) {
            mockDataDTO.setType(MockData.Type.ReturnBody);
            mockDataDTO.setBody(StaticMockService.orderResultMock);
        } else if (methodName.endsWith("testVoid")) {
            mockDataDTO.setType(MockData.Type.VoidReturn);
        } else if (methodName.endsWith("testInt")) {
            mockDataDTO.setType(MockData.Type.ReturnBody);
            mockDataDTO.setBody(StaticMockService.intMock);
        } else if (methodName.endsWith("testInteger")) {
            mockDataDTO.setType(MockData.Type.ReturnBody);
            mockDataDTO.setBody(StaticMockService.intMock);
        } else if (methodName.endsWith("testDouble")) {
            mockDataDTO.setType(MockData.Type.ReturnBody);
            mockDataDTO.setBody(StaticMockService.doubleMock);
        } else if (methodName.endsWith("testArrayList")) {
            mockDataDTO.setType(MockData.Type.ReturnBody);
            mockDataDTO.setBody(StaticMockService.testArrayList);
        } else {
            mockDataDTO.setType(MockData.Type.ReturnBody);
            mockDataDTO.setBody(StaticMockService.orderResultMock);
        }


        return JSON.toJSONString(mockDataDTO);
    }

}
