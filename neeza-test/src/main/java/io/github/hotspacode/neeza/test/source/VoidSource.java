package io.github.hotspacode.neeza.test.source;

import io.github.hotspacode.neeza.deputy.core.MockSpy;
import io.github.hotspacode.neeza.deputy.dto.MockTransport;

public class VoidSource {


    public void createSource() {
        MockTransport mockResponse = MockSpy.getMockData(Thread.currentThread().getStackTrace()[1]);
        if (mockResponse.isMocked()) {
            if (mockResponse.isReturnVoid()) {
                return;
            }
        }


        System.out.println("AAAAAAAAAA");
        return;
    }

}
