package com.manhpd.commandBusPattern.bus;

import com.manhpd.commandBusPattern.commandHandler.*;

import java.util.*;
import java.util.function.Function;

public class CommandBusBuilder {

    private LinkedList<Middleware> middlewares = new LinkedList<>();

    private List<Object> commandHandlerCandidates = new ArrayList<>();

    private List<Object> valueProviderCandidates = new ArrayList<>();

    public CommandBusBuilder registerCommandHandler(Object commandHandler) {
        this.commandHandlerCandidates.add(commandHandler);
        return this;
    }

    public CommandBusBuilder registerValueProvider(Object valueProvider) {
        this.valueProviderCandidates.add(valueProvider);
        return this;
    }

    public CommandBusBuilder registerMiddleware(Middleware middleware) {
        this.middlewares.addFirst(middleware);
        return this;
    }

    public CommandBus build() {
        return new CommandBus(this.middlewares.stream()
                                              .reduce((Function<Object, Object>) new CommandExecutor(this.buildCommandHandlersMapping(this.commandHandlerCandidates,
                                                                                                                                      this.buildValueProvidersMapping(this.valueProviderCandidates))),
                                                       (f, m) -> command -> m.execute(command, f),
                                                       (f1, f2) -> f2
        ));
    }

    /**
     * Based on ValueProvider objects that receive from user
     *
     * @param valueProviderCandidates
     * @return
     */
    public Map<Class, Map<String, ValueProvider>> buildValueProvidersMapping(List<Object> valueProviderCandidates) {
        Map<Class, Map<String, ValueProvider>> valueProvidersMap = new HashMap<>();
        valueProviderCandidates.stream()
                               .flatMap(candidate -> ValueProviderFactory.create(candidate).stream())
                               .forEach(valueProvider -> {
                                   Class returnedType = valueProvider.getProvidedValueDescription().type;
                                   String nameMethod = valueProvider.getProvidedValueDescription().name;

                                   ValueProvider registeredValueProvider = valueProvidersMap.computeIfAbsent(returnedType, k -> new HashMap<>())
                                                                                            .get(nameMethod);
                                   if (Objects.isNull(registeredValueProvider)) {
                                       throw new IllegalStateException();
                                   }

                                   valueProvidersMap.get(returnedType).put(nameMethod, valueProvider);
                               });

        return valueProvidersMap;
    }

    /**
     *
     *
     * @param commandHandlerCandidates
     * @param valueProvidersMapping
     * @return
     */
    public Map<Class, CommandHandler> buildCommandHandlersMapping(List<Object> commandHandlerCandidates,
                                                                  Map<Class, Map<String, ValueProvider>> valueProvidersMapping) {
        Map<Class, CommandHandler> handlerMap = new HashMap<>();
        commandHandlerCandidates.stream()
                                .flatMap(candidate -> CommandHandlerFactory.create(candidate, valueProvidersMapping).stream())
                                .forEach(handlerTuple -> {
                                    Class commandClazz = handlerTuple.getFirst();
                                    CommandHandler commandHandler = handlerTuple.getSecond();
                                    CommandHandler registeredCommandHandler = handlerMap.get(commandClazz);
                                    if (Objects.isNull(registeredCommandHandler)) {
                                        throw new IllegalStateException();
                                    }

                                    handlerMap.put(commandClazz, commandHandler);
                                });
        return handlerMap;
    }

}
