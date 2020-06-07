package io.github.hotspacode.neeza.spy;

import io.github.hotspacode.neeza.base.api.IMockPushSpyService;
import io.github.hotspacode.neeza.base.dto.PushTransportData;
import io.github.hotspacode.neeza.base.util.MethodInvokeUtil;

public class MockPushService {

    public static IMockPushSpyService mockPushSpyService;

    public Object push(PushTransportData transportData) {
        Object service = mockPushSpyService.getService(transportData.getServiceName());
        Object result = MethodInvokeUtil.methodInvoke(service, transportData.getMethodName(), null);

        return result;
    }

}
