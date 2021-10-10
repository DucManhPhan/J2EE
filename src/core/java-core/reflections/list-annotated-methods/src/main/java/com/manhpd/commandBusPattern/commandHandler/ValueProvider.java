package com.manhpd.commandBusPattern.commandHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ValueProvider {

    private Object target;

    private Method method;

    private ParameterTypeDescription providedValueDescription;

    public ValueProvider(Object target, Method method, ParameterTypeDescription providedValueDescription) {
        this.target = target;
        this.method = method;
        this.providedValueDescription = providedValueDescription;
    }

    public Object invoke() {
        try {
            return this.method.invoke(this.target);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new IllegalStateException("Do not execute value provider.");
        }
    }

    public Object getTarget() {
        return target;
    }

    public Method getMethod() {
        return method;
    }

    public ParameterTypeDescription getProvidedValueDescription() {
        return providedValueDescription;
    }
}
