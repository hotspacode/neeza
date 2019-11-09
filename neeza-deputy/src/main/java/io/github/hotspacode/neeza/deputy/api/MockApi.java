package io.github.hotspacode.neeza.deputy.api;


import io.github.hotspacode.neeza.deputy.dto.MethodSpiResponseDTO;

public final class MockApi {

    /**
     * @param stackTraceElement
     * @return
     */
    public static MethodSpiResponseDTO getMockData(StackTraceElement stackTraceElement) {
        System.out.println("调用到mock");
        MethodSpiResponseDTO responseDTO = new MethodSpiResponseDTO(false);
        try {


        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseDTO;
    }

}
