package io.github.hotspacode.neeza.server.standalone;

import com.alibaba.fastjson.JSON;
import io.github.hotspacode.neeza.base.dto.PushTransportData;

public class PushTransportDataTest {
    public static void main(String[] args) {
        PushTransportData pushTransportData = new PushTransportData();
        pushTransportData.setServiceName("testPushService");
        pushTransportData.setMethodName("push1");
        pushTransportData.setParamList(null);

        System.out.println(JSON.toJSONString(pushTransportData));
    }
}
