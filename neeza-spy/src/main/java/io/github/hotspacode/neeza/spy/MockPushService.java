package io.github.hotspacode.neeza.spy;

import io.github.hotspacode.neeza.base.api.IMockPushSpyService;
import io.github.hotspacode.neeza.base.dto.PushTransportData;
import io.github.hotspacode.neeza.base.util.MethodInvokeUtil;
import io.github.hotspacode.neeza.core.serialization.FastJSONSerialization;
import io.github.hotspacode.neeza.spy.provider.MockPushSpyServiceProvider;

public class MockPushService {

    private FastJSONSerialization neezaSerialization = new FastJSONSerialization();
    public static IMockPushSpyService mockPushSpyService = MockPushSpyServiceProvider.getInstance();

    public Object push(PushTransportData transportData) throws ClassNotFoundException {
        if (null == mockPushSpyService) {

        }
        Object service = mockPushSpyService.getService(transportData.getServiceName());
        Object[] params = null;
        if (null != transportData.getParamList() && !transportData.getParamList().isEmpty()) {
            params = new Object[transportData.getParamList().size()];
        }

        for (int i = 0; i < transportData.getParamList().size(); i++) {
            if (null != transportData.getParamList().get(i)) {
                Class targetClass = Class.forName(transportData.getParamList().get(i).getClassName());
                Object deserialize = neezaSerialization.deserialize(transportData.getParamList().get(i).getContent().getBytes(), targetClass);
                params[i] = deserialize;
            } else {
                params[i] = null;
            }
        }

        Object result = MethodInvokeUtil.methodInvoke(service, transportData.getMethodName(), params);

        return result;
    }

}
