package io.github.hotspacode.neeza.server.standalone.service;


import io.github.hotspacode.neeza.base.dto.MockData;
import io.github.hotspacode.neeza.server.standalone.dao.ApiMockDataRepository;
import io.github.hotspacode.neeza.server.standalone.model.ApiMockData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApiMockDataService {

    @Autowired
    private ApiMockDataRepository apiMockDataRepository;

    public MockData getData(String methodDesc, String ip, Integer port) {
        MockData mockDataDTO = new MockData();
        mockDataDTO.setType(MockData.Type.NONE);

        ApiMockData apiMockDataExample = new ApiMockData();
        apiMockDataExample.setApi_name(methodDesc);
        Example<ApiMockData> example = Example.of(apiMockDataExample);

        Optional<ApiMockData> one = apiMockDataRepository.findOne(example);

        if (one.isPresent()) {
            ApiMockData apiMockData = one.get();
            mockDataDTO.setBody(apiMockData.getApi_data());
            switch (apiMockData.getMethod_type()) {
                case 1:
                    mockDataDTO.setType(MockData.Type.ReturnBody);
                    break;
                case 2:
                    mockDataDTO.setType(MockData.Type.VoidReturn);
                    break;
                case 3:
                    mockDataDTO.setType(MockData.Type.ReturnNull);
                    break;
            }
        }

        return mockDataDTO;
    }

}
