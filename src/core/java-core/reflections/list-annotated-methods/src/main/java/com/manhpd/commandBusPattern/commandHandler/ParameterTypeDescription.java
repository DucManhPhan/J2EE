package com.manhpd.commandBusPattern.commandHandler;

public class ParameterTypeDescription {

    /**
     * With value provider, it can be the returned type
     * With Parameter, it can be its type
     */
    public Class type;

    public String name;

    public ParameterTypeDescription(Class type, String name) {
        this.type = type;
        this.name = name;
    }
}
