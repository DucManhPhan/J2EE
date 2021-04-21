package com.manhpd.commandBusPattern.commandHandler;

import com.manhpd.annotation.Provider;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ValueProviderFactory {

    public static List<ValueProvider> create(Object candidate) {
        Set<Method> methods = extractValueProviderMethods(candidate);

        return methods.stream()
                      .map(method -> new ValueProvider(candidate, method,
                                                       extractParameterTypeDescription(method)))
                      .collect(Collectors.toList());
    }

    private static Set<Method> extractValueProviderMethods(Object valueProvider) {
        Set<Method> methods = ReflectionUtils.getAllMethods(valueProvider.getClass(),
                                                            ReflectionUtils.withAnnotation(Provider.class));
        if (methods.stream().anyMatch(method -> method.getParameterCount() > 0)) {
            throw new IllegalStateException();
        }

        return methods;
    }

    private static ParameterTypeDescription extractParameterTypeDescription(Method method) {
        return new ParameterTypeDescription(method.getReturnType(), method.getName());
    }

}
