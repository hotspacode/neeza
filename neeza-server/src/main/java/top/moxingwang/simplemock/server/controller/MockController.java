package top.moxingwang.simplemock.server.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.moxingwang.simplemock.core.dto.MockDataDTO;
import top.moxingwang.simplemock.server.service.StaticMockService;

@RestController
@RequestMapping("/mock")
public class MockController {

    @GetMapping("/string/{methodName}")
    public String mock(@PathVariable(value = "methodName") String methodName) {
        MockDataDTO mockDataDTO = new MockDataDTO();
        mockDataDTO.setType(MockDataDTO.Type.NONE);

        System.out.println("mock服务被调用到" + methodName);

        if (methodName.endsWith("testMap")) {
            mockDataDTO.setType(MockDataDTO.Type.ReturnBody);
            mockDataDTO.setBody(StaticMockService.mapMock);
        } else if (methodName.endsWith("testString")) {
            mockDataDTO.setType(MockDataDTO.Type.ReturnBody);
            mockDataDTO.setBody(StaticMockService.mapMock);
        } else if (methodName.endsWith("testOrderResult")) {
            mockDataDTO.setType(MockDataDTO.Type.ReturnBody);
            mockDataDTO.setBody(StaticMockService.orderResultMock);
        } else if (methodName.endsWith("testVoid")) {
            mockDataDTO.setType(MockDataDTO.Type.VoidReturn);
        } else if (methodName.endsWith("testInt")) {
            mockDataDTO.setType(MockDataDTO.Type.ReturnBody);
            mockDataDTO.setBody(StaticMockService.intMock);
        } else if (methodName.endsWith("testInteger")) {
            mockDataDTO.setType(MockDataDTO.Type.ReturnBody);
            mockDataDTO.setBody(StaticMockService.intMock);
        } else if (methodName.endsWith("testDouble")) {
            mockDataDTO.setType(MockDataDTO.Type.ReturnBody);
            mockDataDTO.setBody(StaticMockService.doubleMock);
        } else if (methodName.endsWith("testArrayList")) {
            mockDataDTO.setType(MockDataDTO.Type.ReturnBody);
            mockDataDTO.setBody(StaticMockService.testArrayList);
        }else {
            mockDataDTO.setType(MockDataDTO.Type.ReturnBody);
            mockDataDTO.setBody(StaticMockService.orderResultMock);
        }


        return JSON.toJSONString(mockDataDTO);
    }

}
