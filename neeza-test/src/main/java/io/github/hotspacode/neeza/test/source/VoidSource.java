package io.github.hotspacode.neeza.test.source;

import io.github.hotspacode.neeza.core.api.MockApi;
import io.github.hotspacode.neeza.core.dto.MethodSpiResponseDTO;

public class VoidSource {


    public void createSource() {
        MethodSpiResponseDTO mockResponse = MockApi.getMockData(Thread.currentThread().getStackTrace()[1]);
        if (mockResponse.isMocked()) {
            if (mockResponse.isReturnVoid()) {
                return;
            }
        }


        System.out.println("AAAAAAAAAA");
        return;
    }

}
