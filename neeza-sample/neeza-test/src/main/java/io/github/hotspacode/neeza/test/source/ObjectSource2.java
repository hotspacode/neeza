package io.github.hotspacode.neeza.test.source;

import io.github.hotspacode.neeza.base.core.MockSpy;
import io.github.hotspacode.neeza.base.dto.MockTransport;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ObjectSource2 {
    public static void main(String[] args) {
        Class<ObjectSource2> objectSource2Class = ObjectSource2.class;
        for (Method method : objectSource2Class.getMethods()) {
            int count = method.getParameterCount();

            System.out.println(1);
        }

    }


    public Map createSource0() {
        List<Object> methodParams = new ArrayList<>();

        MockTransport mockResponse = MockSpy.getMockData(Thread.currentThread().getStackTrace()[1],methodParams,null);
        if (mockResponse.isMocked()) {
            return mockResponse.getObject(mockResponse);
        }

        String dd = "111";

        System.out.println("我在正常执行...");
        return null;
    }

   public Map createSource1(String a,String b ) {
       List<Object> methodParams = new ArrayList<>();
       methodParams.add(a);
       methodParams.add(b);

       MockTransport mockResponse = MockSpy.getMockData(Thread.currentThread().getStackTrace()[1],methodParams,null);
       if (mockResponse.isMocked()) {
           return mockResponse.getObject(mockResponse);
       }

       String dd = "111";

        System.out.println("我在正常执行...");
        return null;
    }


    public Map createSource2(String a,String b ,String c,String d ) {
        List<Object> methodParams = new ArrayList<>();
        methodParams.add(a);
        methodParams.add(b);
        methodParams.add(c);
        methodParams.add(d);

   /*     MockTransport mockResponse = MockSpy.getMockData(Thread.currentThread().getStackTrace()[1]);
        if (mockResponse.isMocked()) {
            return mockResponse.getObject(mockResponse);
        }*/


        System.out.println("我在正常执行...");
        return null;
    }


}
