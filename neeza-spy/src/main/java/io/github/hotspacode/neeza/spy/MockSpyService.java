package io.github.hotspacode.neeza.spy;

import io.github.hotspacode.neeza.base.api.IMockSpyService;
import io.github.hotspacode.neeza.base.dto.MockData;
import io.github.hotspacode.neeza.base.dto.MockTransport;
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
import java.util.concurrent.ConcurrentHashMap;

public class MockSpyService implements IMockSpyService {
    private static final ConcurrentHashMap<String, MockData> cache = new ConcurrentHashMap<>();
    private FastJSONSerialization neezaSerialization = new FastJSONSerialization();


    public static void expireKey(String key) {
        cache.remove(key);
    }

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


        //调用本地内存

        //调用mock server
        //todo 指定为方法签名
//            String mockUrl = System.getProperty(NeezaConstant.SIMPLE_MOCK_VM_SERVER_URL) + targetMethod.getDeclaringClass().getName() + "." + targetMethod.toGenericString();
//            String responseStr = org.apache.http.util.EntityUtils.toString(org.apache.http.impl.client.HttpClients.createDefault().execute(new org.apache.http.client.methods.HttpGet(mockUrl)).getEntity(), "UTF-8");

        String methodDesc = targetMethod.getDeclaringClass().getName() + "." + targetMethod.toGenericString();
        MockData mockData = null;

        if ((mockData = cache.get(methodDesc)) == null) {
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("methodDesc", methodDesc);
            paramMap.put("clientPort", TransportServerStatus.getRealPort() + "");

            try {
                CompletableFuture<String> mockDataCompletableFuture = NeezaServer.getTransportClient().execute("neeza/mock/pull", paramMap, false)
                        .thenApply(json -> {
                            return json;
                        });
                String responseStr = mockDataCompletableFuture.get();

                //todo 全局参数mock报错是否支持继续

                if (StringUtil.isNotBlank(responseStr)) {
                    mockData = neezaSerialization.deserialize(responseStr.getBytes(), MockData.class);
                }

                cache.put(methodDesc, mockData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (null == mockData || MockData.Type.NONE.equals(mockData.getType())) {
            mockTransport.setMocked(false);
        } else {
            mockTransport.setMocked(true);

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
