package io.github.hotspacode.neeza.server.standalone.service;

import io.github.hotspacode.neeza.server.standalone.dao.ApiMockDataRepository;
import io.github.hotspacode.neeza.server.standalone.model.ApiMockData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DataEditService {
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private ApiMockDataRepository apiMockDataRepository;

    public void publish(String methodDesc, String content, Integer methodType) {
        ApiMockData apiMockDataExample = new ApiMockData();
        apiMockDataExample.setApi_name(methodDesc);
        Example<ApiMockData> example = Example.of(apiMockDataExample);

        ApiMockData apiMockData = null;
        Optional<ApiMockData> one = apiMockDataRepository.findOne(example);
        if (one.isPresent()) {
            apiMockData = one.get();
            if (content.equals(apiMockData.getApi_data())) {
                return;
            } else {
                apiMockData.setApi_data(content);
            }
        } else {
            apiMockData = new ApiMockData();
            apiMockData.setApp_id("0");
            apiMockData.setApi_name(methodDesc);
            apiMockData.setApi_data(content);
            apiMockData.setMethod_type(methodType);
        }
        //落库
        if (null == apiMockData.getId()) {
            apiMockDataRepository.save(apiMockData);
        } else {
            apiMockDataRepository.updateContentById(apiMockData.getId(), apiMockData.getApi_data());
        }

        //通知应用
        noticeService.noticeClient(methodDesc);
    }

    public void push() {

    }
}
