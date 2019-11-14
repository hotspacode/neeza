package io.github.hotspacode.neeza.test.source;

import io.github.hotspacode.neeza.deputy.core.MockSpy;
import io.github.hotspacode.neeza.deputy.dto.MockTransport;

import java.util.ArrayList;
import java.util.List;

public class VoidSource {


    public void createSource(String a, String b) {
        List<Object> methodParams = new ArrayList<>();
        methodParams.add(a);
        methodParams.add(b);

        MockTransport mockResponse = MockSpy.getMockData(Thread.currentThread().getStackTrace()[1],methodParams,"");
        if (mockResponse.isMocked()) {
            if (mockResponse.isReturnVoid()) {
                return;
            }
        }


        System.out.println("AAAAAAAAAA");
        return;
    }

}
