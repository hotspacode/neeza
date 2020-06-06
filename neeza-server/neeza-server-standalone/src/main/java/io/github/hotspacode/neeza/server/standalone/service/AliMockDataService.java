package io.github.hotspacode.neeza.server.standalone.service;


import io.github.hotspacode.neeza.base.dto.MockData;
import io.github.hotspacode.neeza.server.standalone.dao.ApiMockDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Service;

@Service
public class AliMockDataService {

    @Autowired
    private ApiMockDataRepository apiMockDataRepository;

    public MockData getData(String methodDesc){
        MockData mockDataDTO = new MockData();
        mockDataDTO.setType(MockData.Type.NONE);


        return mockDataDTO;
    }

}
