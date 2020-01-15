package io.github.hotspacode.neeza.base.core;

import io.github.hotspacode.neeza.base.api.IMockSpyService;
import io.github.hotspacode.neeza.base.dto.MockTransport;
import io.github.hotspacode.neeza.base.service.MockSpyServiceProvider;

import java.util.List;

public class MockSpy {

    private static IMockSpyService mockSpyService = MockSpyServiceProvider.getInstance();

    public static MockTransport getMockData(StackTraceElement stackTraceElement, List<Object> localVariable, String argumentTypeDescriptors) {
        System.out.println("调用到mock");
        MockTransport mockTransport = null;

        try {
            mockTransport = mockSpyService.transport(stackTraceElement.getClassName(), stackTraceElement.getMethodName(), argumentTypeDescriptors, localVariable);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null == mockTransport) {
                mockTransport = new MockTransport(false);
            }
        }

        return mockTransport;
    }



  /*  public static MockTransport getMockData(StackTraceElement stackTraceElement, List<Object> localVariable) {
        System.out.println("调用到mock");
        MockTransport mockTransport = null;

        try {

            Class targetClass = Class.forName(stackTraceElement.getClassName());
            String targetMethodName = stackTraceElement.getMethodName();

            Method targetMethod = null;
            for (Method method : targetClass.getDeclaredMethods()) {
                if (method.getName().equals(targetMethodName) && localVariable.size() == method.getParameterCount()) {

                    targetMethod = method;
                    break;
                }
            }


            mockTransport = mockSpyService.transport(targetMethod, localVariable);

            if (null == mockTransport) {
                mockTransport = new MockTransport(false);
                return mockTransport;
            }

            mockTransport.setMethodReturnClass(targetMethod.getReturnType());
            mockTransport.setPrimitive(targetMethod.getReturnType().isPrimitive());
            mockTransport.getMethodReturnClass().isPrimitive();

            mockTransport.setNeezaSerialization(neezaSerialization);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null == mockTransport) {
                mockTransport = new MockTransport(false);
            }
        }

        return mockTransport;
    }*/

}
