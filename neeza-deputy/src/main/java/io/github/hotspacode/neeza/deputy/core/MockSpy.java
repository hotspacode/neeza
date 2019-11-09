package io.github.hotspacode.neeza.deputy.core;

import io.github.hotspacode.neeza.deputy.api.IMockSpyService;
import io.github.hotspacode.neeza.deputy.api.INeezaSerialization;
import io.github.hotspacode.neeza.deputy.dto.MockTransport;
import io.github.hotspacode.neeza.deputy.service.MockSpyServiceProvider;
import io.github.hotspacode.neeza.deputy.service.NeezaSerializationProvider;

import java.lang.reflect.Method;

public class MockSpy {

    private static INeezaSerialization neezaSerialization = NeezaSerializationProvider.getInstance();
    private static IMockSpyService mockSpyService = MockSpyServiceProvider.getInstance();

    /**
     * @param stackTraceElement
     * @return
     */
    public static MockTransport getMockData(StackTraceElement stackTraceElement) {
        System.out.println("调用到mock");
        MockTransport mockTransport = null;

        try {

            Class targetClass = Class.forName(stackTraceElement.getClassName());
            String targetMethodName = stackTraceElement.getMethodName();

            mockTransport = mockSpyService.transport(targetClass.getName(), targetMethodName);

            if (null == mockTransport) {
                mockTransport = new MockTransport(false);
                return mockTransport;
            }

            mockTransport.setNeezaSerialization(neezaSerialization);

            for (Method method : targetClass.getMethods()) {
                if (method.getName().equals(targetMethodName)) {
                    mockTransport.setMethodReturnClass(method.getReturnType());
                    mockTransport.setPrimitive(method.getReturnType().isPrimitive());
                    mockTransport.getMethodReturnClass().isPrimitive();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null == mockTransport) {
                mockTransport = new MockTransport(false);
            }
        }

        return mockTransport;
    }
}
