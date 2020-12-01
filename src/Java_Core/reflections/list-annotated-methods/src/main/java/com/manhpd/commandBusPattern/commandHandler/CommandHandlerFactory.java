package com.manhpd.commandBusPattern.commandHandler;

import com.manhpd.annotation.Handler;
import com.manhpd.utils.Tuple;
import org.reflections.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommandHandlerFactory {

    /**
     *
     * @param candidate - The CommandHandler object
     * @param valueProviderMap -
     * @return
     */
    public static List<Tuple<Class, CommandHandler>> create(Object candidate,
                                                            Map<Class, Map<String, ValueProvider>> valueProviderMap) {
        Set<Method> methods = extractCommandHandlerMethods(candidate);
        return methods.stream()
                      .map(method -> {
                          List<ParameterTypeDescription> params = extractParams(method);
                          List<ValueProvider> valueProviders = getValueProviders(candidate.getClass(), valueProviderMap, params);

                          return new Tuple<>(params.get(0).type,
                                  new CommandHandler(candidate, method, valueProviders));
                      })
                      .collect(Collectors.toList());
    }

    private static Set<Method> extractCommandHandlerMethods(Object commandHandler) {
//        Set<Method> methods = getDeclaredMethodWithAnnotation(commandHandler.getClass(), Handler.class);
        Set<Method> methods = ReflectionUtils.getAllMethods(commandHandler.getClass(),
                                                            ReflectionUtils.withAnnotation(Handler.class));
        if (methods.stream().anyMatch(method -> method.getParameterCount() == 0)) {
            throw new IllegalStateException("Do not have enough parameters");
        }

        return methods;
    }

    private static List<ParameterTypeDescription> extractParams(Method method) {
        return Arrays.stream(method.getParameters())
                     .map(param -> new ParameterTypeDescription(param.getType(), param.getName()))
                     .collect(Collectors.toList());
    }

    private static List<ValueProvider> getValueProviders(Class commandHandlerClazz,
                                                         Map<Class, Map<String, ValueProvider>> valueProviderMap,
                                                         List<ParameterTypeDescription> params) {
        return params.stream()
                     .skip(1) // the first parameter is the Command class
                     .map(description -> {
                         Map<String, ValueProvider> possibleProviders = valueProviderMap.get(description.type);
                         if (Objects.isNull(possibleProviders) || possibleProviders.isEmpty()) {
                             throw new IllegalStateException();
                         }

                         if (possibleProviders.size() == 1) {
                             return possibleProviders.values().iterator().next();
                         }

                         ValueProvider valueProvider = possibleProviders.get(description.name);
                         if (Objects.isNull(valueProvider)) {
                             throw new IllegalStateException();
                         }

                         return valueProvider;
                     })
                     .collect(Collectors.toList());
    }

    private static Set<Method> getDeclaredMethodWithAnnotation(Class commandHandlerClazz, Class annotationClazz) {
        Method[] methods = commandHandlerClazz.getDeclaredMethods();
        return Stream.of(methods).filter(method -> {
            Annotation annotationOfMethod = method.getAnnotation(annotationClazz);
            return Objects.nonNull(annotationOfMethod);
        }).collect(Collectors.toSet());
    }

}
