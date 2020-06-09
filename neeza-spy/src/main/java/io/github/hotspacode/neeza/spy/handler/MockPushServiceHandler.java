package io.github.hotspacode.neeza.spy.handler;

import com.alibaba.fastjson.JSON;
import io.github.hotspacode.neeza.base.dto.PushTransportData;
import io.github.hotspacode.neeza.base.log.NeezaLog;
import io.github.hotspacode.neeza.spy.MockPushService;
import io.github.hotspacode.neeza.transport.api.annotation.CommandMapping;
import io.github.hotspacode.neeza.transport.api.command.CommandHandler;
import io.github.hotspacode.neeza.transport.api.command.CommandRequest;
import io.github.hotspacode.neeza.transport.api.command.CommandResponse;

@CommandMapping(name = "neeza/spy/mock/data/push", desc = "neeza mock data push handler")
public class MockPushServiceHandler implements CommandHandler<String> {
    @Override
    public CommandResponse<String> handle(CommandRequest request) {
        CommandResponse<String> commandResponse = CommandResponse.ofSuccess(null);
        String body = request.getParam("body");

        NeezaLog.info("data push {0}", body);

        PushTransportData pushTransportData = JSON.parseObject(body, PushTransportData.class);

        try {
            Object pushResult = MockPushService.push(pushTransportData);
            return CommandResponse.ofSuccess(JSON.toJSONString(pushResult));
        } catch (ClassNotFoundException e) {
            NeezaLog.warn("data push error {0}", body, e);
            return CommandResponse.ofFailure(e);
        }
    }

}
