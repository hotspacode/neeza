package io.github.hotspacode.neeza.test.source;

import io.github.hotspacode.neeza.base.core.MockSpy;
import io.github.hotspacode.neeza.base.dto.MockTransport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ObjectSource3 {

    public Map createSource2(String a, String b) {
        List<Object> methodParams = new ArrayList<>();

        MockTransport mockResponse = MockSpy.getMockData(Thread.currentThread().getStackTrace()[1],methodParams,null);
        if (mockResponse.isMocked()) {
            return mockResponse.getObject(mockResponse);
        }


        System.out.println("我在正常执行...");
        return null;
    }


}
