package io.github.hotspacode.neeza.spy.handler;

import io.github.hotspacode.neeza.spy.MockSpyService;
import io.github.hotspacode.neeza.transport.api.annotation.CommandMapping;
import io.github.hotspacode.neeza.transport.api.command.CommandHandler;
import io.github.hotspacode.neeza.transport.api.command.CommandRequest;
import io.github.hotspacode.neeza.transport.api.command.CommandResponse;

@CommandMapping(name = "spy/mock/data/change", desc = "neeza mock data change handler")
public class MockDataChangeHandler implements CommandHandler<String> {

    @Override
    public CommandResponse<String> handle(CommandRequest request) {
        if (null == request.getBody()) {
            return CommandResponse.ofSuccess("body is NULL!");
        }

        MockSpyService.expireKey(new String(request.getBody()));

        return CommandResponse.ofSuccess("received!");
    }


}
