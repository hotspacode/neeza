package io.github.hotspacode.neeza.core.api;

import com.alibaba.fastjson.JSON;
import io.github.hotspacode.neeza.core.NeezaMockConstant;
import io.github.hotspacode.neeza.core.dto.MethodSpiResponseDTO;
import io.github.hotspacode.neeza.core.dto.MockDataDTO;

import java.lang.reflect.Method;

public final class MockApi {

    /**
     * @param stackTraceElement
     * @return
     */
    public static MethodSpiResponseDTO getMockData(StackTraceElement stackTraceElement) {
        System.out.println("调用到mock");
        MethodSpiResponseDTO responseDTO = new MethodSpiResponseDTO(false);
        try {
            if (null == System.getProperty(NeezaMockConstant.SIMPLE_MOCK_VM_SERVER_URL)) {
                responseDTO.setMocked(false);
                return responseDTO;
            }
            Class cl = Class.forName(stackTraceElement.getClassName());
            for (Method method : cl.getMethods()) {
                if (method.getName().equals(stackTraceElement.getMethodName())) {
                    responseDTO.setMethodReturnClass(method.getReturnType());
                    responseDTO.setPrimitive(method.getReturnType().isPrimitive());
                    responseDTO.getMethodReturnClass().isPrimitive();

                    //调用mock server
                    String mockUrl = System.getProperty(NeezaMockConstant.SIMPLE_MOCK_VM_SERVER_URL) + cl.getName() + "." + method.getName();
                    String responseStr = org.apache.http.util.EntityUtils.toString(org.apache.http.impl.client.HttpClients.createDefault().execute(new org.apache.http.client.methods.HttpGet(mockUrl)).getEntity(), "UTF-8");

                    //todo 全局参数mock报错是否支持继续

                    MockDataDTO mockDataDTO = JSON.parseObject(responseStr, MockDataDTO.class);

                    if (MockDataDTO.Type.NONE.equals(mockDataDTO.getType())) {
                        responseDTO.setMocked(false);
                    } else {
                        responseDTO.setMocked(true);

                        if (MockDataDTO.Type.ReturnBody.equals(mockDataDTO.getType())) {
                            responseDTO.setResponse(mockDataDTO.getBody());
                        } else if (MockDataDTO.Type.VoidReturn.equals(mockDataDTO.getType())) {
                            responseDTO.setReturnVoid(true);
                        } else if (MockDataDTO.Type.ReturnNull.equals(mockDataDTO.getType())) {
                            responseDTO.setReturnNull(true);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseDTO;
    }

}
