package top.moxingwang.simplemock.test.source;

import top.moxingwang.simplemock.core.api.MockApi;
import top.moxingwang.simplemock.core.dto.MethodSpiResponseDTO;

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
