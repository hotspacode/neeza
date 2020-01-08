package io.github.hotspacode.neeza.transport.api.util;

import io.github.hotspacode.neeza.transport.api.command.CommandRequest;

/**
 * @author moxingwang
 */
public class CommandUtil {
    public static final String REQUEST_TARGET = "command-target";

    public static String getTarget(CommandRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        return request.getMetadata().get(REQUEST_TARGET);
    }

}
