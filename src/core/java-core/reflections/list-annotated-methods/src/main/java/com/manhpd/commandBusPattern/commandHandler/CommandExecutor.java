package com.manhpd.commandBusPattern.commandHandler;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class CommandExecutor implements Function<Object, Object> {

    private Map<Class, CommandHandler> commandHandlers;

    public CommandExecutor(Map<Class, CommandHandler> commandHandlers) {
        this.commandHandlers = commandHandlers;
    }

    @Override
    public Object apply(Object command) {
        CommandHandler commandHandler = this.commandHandlers.get(command.getClass());
        if (Objects.isNull(commandHandler)) {
            throw new IllegalStateException("Do not found command handler that is corresponding to " + command.getClass().getName());
        }

        return commandHandler.invoke(command);
    }
}
