package com.manhpd.commandBusPattern.bus;

import java.util.function.Function;

public class CommandBus {

    private Function<Object, Object> commandProcessor;

    public CommandBus(Function<Object, Object> commandProcessor) {
        this.commandProcessor = commandProcessor;
    }

    public <R> R execute(Object command) {
        return (R) this.commandProcessor.apply(command);
    }

}
