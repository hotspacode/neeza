package top.moxingwang.simplemock.test.source;

import top.moxingwang.simplemock.core.api.MockApi;
import top.moxingwang.simplemock.core.dto.MethodSpiResponseDTO;

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
