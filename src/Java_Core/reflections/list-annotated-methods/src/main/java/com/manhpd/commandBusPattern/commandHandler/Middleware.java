package com.manhpd.commandBusPattern.commandHandler;

import java.util.function.Function;

public interface Middleware {

    <R> R execute(Object command, Function<Object, R> next);

}
