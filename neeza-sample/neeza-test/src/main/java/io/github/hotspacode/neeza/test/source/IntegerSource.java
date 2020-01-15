package io.github.hotspacode.neeza.test.source;

import io.github.hotspacode.neeza.base.core.MockSpy;
import io.github.hotspacode.neeza.base.dto.MockTransport;

public class IntegerSource {


    public int createSource() {
     /*   MockTransport mockResponse = MockSpy.getMockData(Thread.currentThread().getStackTrace()[1]);
        if (mockResponse.isMocked()) {
            return mockResponse.getObject(mockResponse);
        }*/

        System.out.println("AAAAAAAAAA");
        return 1 ;
    }

}
