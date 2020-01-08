package io.github.hotspacode.neeza.transport.api.command;

import io.github.hotspacode.neeza.core.util.StringUtil;
import io.github.hotspacode.neeza.deputy.common.SpiLoader;
import io.github.hotspacode.neeza.transport.api.annotation.CommandMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandHandlerProvider {

    public Map<String, CommandHandler> namedHandlers() {
        Map<String, CommandHandler> map = new HashMap<String, CommandHandler>();

        List<CommandHandler> commandHandlers = SpiLoader.loadInstanceList(CommandHandler.class);

        for (CommandHandler handler : commandHandlers) {
            String name = parseCommandName(handler);
            if (!StringUtil.isEmpty(name)) {
                map.put(name, handler);
            }
        }
        return map;
    }

    private String parseCommandName(CommandHandler handler) {
        CommandMapping commandMapping = handler.getClass().getAnnotation(CommandMapping.class);
        if (commandMapping != null) {
            return commandMapping.name();
        } else {
            return null;
        }
    }

    private static final CommandHandlerProvider INSTANCE = new CommandHandlerProvider();

    public static CommandHandlerProvider getInstance() {
        return INSTANCE;
    }
}
