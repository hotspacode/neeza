package io.github.hotspacode.neeza.deputy.core;

import io.github.hotspacode.neeza.deputy.dto.MockTransport;

public class MockSpy {
    /**
     * @param stackTraceElement
     * @return
     */
    public static MockTransport getMockData(StackTraceElement stackTraceElement) {
        System.out.println("调用到mock");
        MockTransport responseDTO = new MockTransport(false);
        try {


        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseDTO;
    }
}
