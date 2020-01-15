package io.github.hotspacode.neeza.test.source;

import io.github.hotspacode.neeza.base.core.MockSpy;
import io.github.hotspacode.neeza.base.dto.MockTransport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ObjectSource {

    public Map<String, String> createSource1(String aaa) {
        List<Object> localVariable = new ArrayList<>();
        localVariable.add(aaa);
        MockTransport mockResponse = MockSpy.getMockData(Thread.currentThread().getStackTrace()[1],localVariable,null);
        if (mockResponse.isMocked()) {
            return mockResponse.getObject(mockResponse);
        }


        System.out.println("我在正常执行...");
        return null;
    }


    public Map<String, String> createSource2(String aaa,List list,Object ddd) {
        List<Object> localVariable = new ArrayList<>();
        localVariable.add(aaa);
        localVariable.add(list);
        localVariable.add(ddd);
        MockTransport mockResponse = MockSpy.getMockData(Thread.currentThread().getStackTrace()[1],localVariable,null);
        if (mockResponse.isMocked()) {
            return mockResponse.getObject(mockResponse);
        }


        System.out.println("我在正常执行...");
        return null;
    }


}
