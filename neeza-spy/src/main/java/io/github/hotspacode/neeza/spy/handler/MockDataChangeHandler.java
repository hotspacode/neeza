package io.github.hotspacode.neeza.spy.handler;

import io.github.hotspacode.neeza.transport.api.annotation.CommandMapping;
import io.github.hotspacode.neeza.transport.api.command.CommandHandler;
import io.github.hotspacode.neeza.transport.api.command.CommandRequest;
import io.github.hotspacode.neeza.transport.api.command.CommandResponse;

@CommandMapping(name = "spy/mock/data/change", desc = "neeza mock data change handler")
public class MockDataChangeHandler implements CommandHandler<String> {

    @Override
    public CommandResponse<String> handle(CommandRequest request) {

        return CommandResponse.ofSuccess("测试handler!");
    }


}
