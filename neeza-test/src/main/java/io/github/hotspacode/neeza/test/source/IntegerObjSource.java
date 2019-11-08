package io.github.hotspacode.neeza.test.source;

import io.github.hotspacode.neeza.core.api.MockApi;
import io.github.hotspacode.neeza.core.dto.MethodSpiResponseDTO;

public class IntegerObjSource {


    public Integer createSource() {
        MethodSpiResponseDTO mockResponse = MockApi.getMockData(Thread.currentThread().getStackTrace()[1]);
        if (mockResponse.isMocked()) {
            return mockResponse.getObject(mockResponse);
        }


        System.out.println("AAAAAAAAAA");
        return 1;
    }

}
