package io.github.hotspacode.neeza.spy;

import io.github.hotspacode.neeza.base.api.IMockSpyService;
import io.github.hotspacode.neeza.base.dto.MockData;
import io.github.hotspacode.neeza.base.dto.MockTransport;
import io.github.hotspacode.neeza.base.log.NeezaLog;
import io.github.hotspacode.neeza.base.util.StringUtil;
import io.github.hotspacode.neeza.core.serialization.FastJSONSerialization;
import io.github.hotspacode.neeza.transport.api.TransportServerStatus;
import org.objectweb.asm.Type;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class MockSpyService implements IMockSpyService {
    private FastJSONSerialization neezaSerialization = new FastJSONSerialization();

    @Override
    public MockTransport transport(String targetClassName, String targetMethodName, String argumentTypeDescriptors, List<Object> localVariable) throws ClassNotFoundException {
        MockTransport mockTransport = new MockTransport(false);

        Class targetClass = Class.forName(targetClassName);

        String[] argumentTypeDescriptorsArray = argumentTypeDescriptors.split(",");

        Method targetMethod = null;
        for (Method method : targetClass.getDeclaredMethods()) {
            if (method.getName().equals(targetMethodName) && localVariable.size() == method.getParameterCount()) {
                if (argumentTypeDescriptorsArray.length == 0) {
                    targetMethod = method;
                    break;
                } else {
                    Type[] argumentTypes = Type.getArgumentTypes(method);
                    for (Type argumentType : argumentTypes) {
                        for (String argumentTypeDescriptor : argumentTypeDescriptorsArray) {
                            if (!Objects.equals(argumentType.getDescriptor(), argumentTypeDescriptor)) {
                                continue;
                            }
                        }
                    }
                    targetMethod = method;
                    break;
                }
            }
        }

        if (null == targetMethod) {
            return mockTransport;
        } else {
            mockTransport.setMethodReturnClass(targetMethod.getReturnType());
            mockTransport.setPrimitive(targetMethod.getReturnType().isPrimitive());
            mockTransport.getMethodReturnClass().isPrimitive();
            mockTransport.setNeezaSerialization(neezaSerialization);
        }

        String methodDesc = targetMethod.getDeclaringClass().getName() + "." + targetMethod.toGenericString();
        MockData mockData = null;

        if ((mockData = NeezaServer.getMethodMockCache(methodDesc)) == null) {
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("methodDesc", methodDesc);
            paramMap.put("clientPort", TransportServerStatus.getRealPort() + "");

            try {
                CompletableFuture<String> mockDataCompletableFuture = NeezaServer.getTransportClient().execute(NeezaServer.getServerIp(), NeezaServer.getServerPort(), "neeza/mock/pull", paramMap, false)
                        .thenApply(json -> {
                            return json;
                        });
                String responseStr = mockDataCompletableFuture.get();

                if (StringUtil.isNotBlank(responseStr)) {
                    mockData = neezaSerialization.deserialize(responseStr.getBytes(), MockData.class);
                }

                NeezaServer.cacheMethodMock(methodDesc, mockData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (null == mockData || MockData.Type.NONE.equals(mockData.getType())) {
            mockTransport.setMocked(false);
        } else {
            mockTransport.setMocked(true);

            if (StringUtil.isNotBlank(mockData.getMethodReturnClass())) {
                try {
                    mockTransport.setMethodReturnClass(Class.forName(mockData.getMethodReturnClass()));
                } catch (Exception e) {
                    NeezaLog.warn("错误的方法返回类型" + targetMethodName, e);
                }
            }

            if (MockData.Type.ReturnBody.equals(mockData.getType())) {
                mockTransport.setResponse(mockData.getBody());
            } else if (MockData.Type.VoidReturn.equals(mockData.getType())) {
                mockTransport.setReturnVoid(true);
            } else if (MockData.Type.ReturnNull.equals(mockData.getType())) {
                mockTransport.setReturnNull(true);
            }
        }

        return mockTransport;
    }


}
