package io.github.hotspacode.neeza.transport.netty.http.handler;

import io.github.hotspacode.neeza.transport.api.annotation.CommandMapping;
import io.github.hotspacode.neeza.transport.api.command.CommandHandler;
import io.github.hotspacode.neeza.transport.api.command.CommandRequest;
import io.github.hotspacode.neeza.transport.api.command.CommandResponse;

/**
 * @author moxingwang
 */
@CommandMapping(name = "test", desc = "get all active rules by type, request param: type={ruleType}")
public class TestCommandHandler implements CommandHandler<String> {
    @Override
    public CommandResponse<String> handle(CommandRequest request) {
        return CommandResponse.ofSuccess("测试handler!");
    }

}
