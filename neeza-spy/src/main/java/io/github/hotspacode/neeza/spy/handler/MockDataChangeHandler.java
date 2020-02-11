package io.github.hotspacode.neeza.spy.handler;

import io.github.hotspacode.neeza.base.log.NeezaLog;
import io.github.hotspacode.neeza.spy.MockSpyService;
import io.github.hotspacode.neeza.transport.api.annotation.CommandMapping;
import io.github.hotspacode.neeza.transport.api.command.CommandHandler;
import io.github.hotspacode.neeza.transport.api.command.CommandRequest;
import io.github.hotspacode.neeza.transport.api.command.CommandResponse;

@CommandMapping(name = "neeza/spy/mock/data/change", desc = "neeza mock data change handler")
public class MockDataChangeHandler implements CommandHandler<String> {

    @Override
    public CommandResponse<String> handle(CommandRequest request) {
        String methodDesc = request.getParam("methodDesc");

        NeezaLog.info("method expire {0}",methodDesc);

        MockSpyService.expireKey(methodDesc);

        return CommandResponse.ofSuccess("received!");
    }


}
