package io.github.hotspacode.neeza.transport.api.command;

/**
 * @author moxingwang
 */
public interface CommandHandler<T> {
    CommandResponse<T> handle(CommandRequest request);
}