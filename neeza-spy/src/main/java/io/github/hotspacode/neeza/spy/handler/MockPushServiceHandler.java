package io.github.hotspacode.neeza.spy.handler;

import io.github.hotspacode.neeza.base.log.NeezaLog;
import io.github.hotspacode.neeza.transport.api.annotation.CommandMapping;
import io.github.hotspacode.neeza.transport.api.command.CommandHandler;
import io.github.hotspacode.neeza.transport.api.command.CommandRequest;
import io.github.hotspacode.neeza.transport.api.command.CommandResponse;

@CommandMapping(name = "neeza/spy/mock/data/push", desc = "neeza mock data push handler")
public class MockPushServiceHandler implements CommandHandler<String> {
    @Override
    public CommandResponse<String> handle(CommandRequest request) {
        String methodDesc = request.getParam("methodDesc");

        NeezaLog.info("data push {0}",methodDesc);


        return null;
    }
}
