package io.github.hotspacode.neeza.spy.service;

import io.github.hotspacode.neeza.core.NeezaMockConstant;
import io.github.hotspacode.neeza.core.serialization.FastJSONSerialization;
import io.github.hotspacode.neeza.deputy.api.IMockSpyService;
import io.github.hotspacode.neeza.deputy.dto.MockData;
import io.github.hotspacode.neeza.deputy.dto.MockTransport;
import org.objectweb.asm.Type;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

public class MockSpyService implements IMockSpyService {
    private static FastJSONSerialization neezaSerialization = new FastJSONSerialization();

    @Override
    public MockTransport transport(String targetClassName,String targetMethodName ,String argumentTypeDescriptors, List<Object> localVariable) {
        MockTransport mockTransport = new MockTransport(false);

        try {
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
            }else {
                mockTransport.setMethodReturnClass(targetMethod.getReturnType());
                mockTransport.setPrimitive(targetMethod.getReturnType().isPrimitive());
                mockTransport.getMethodReturnClass().isPrimitive();
                mockTransport.setNeezaSerialization(neezaSerialization);
            }


            //调用本地内存

            //调用mock server
            //todo 指定为方法签名
            String mockUrl = System.getProperty(NeezaMockConstant.SIMPLE_MOCK_VM_SERVER_URL) + targetMethod.getDeclaringClass().getName() + "." + targetMethod.toGenericString();
            String responseStr = org.apache.http.util.EntityUtils.toString(org.apache.http.impl.client.HttpClients.createDefault().execute(new org.apache.http.client.methods.HttpGet(mockUrl)).getEntity(), "UTF-8");

            //todo 全局参数mock报错是否支持继续

            MockData mockData = neezaSerialization.deserialize(responseStr.getBytes(), MockData.class);


            if (MockData.Type.NONE.equals(mockData.getType())) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mockTransport;
    }
}
