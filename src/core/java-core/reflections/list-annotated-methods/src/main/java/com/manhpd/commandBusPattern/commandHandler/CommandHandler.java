package com.manhpd.commandBusPattern.commandHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Below is the format of our CommandHandler class.
 * public class CommandHandler {
 *      @Handler
 *      public Integer handle(MyCommand cmd) {
 *          return 5;
 *      }
 *
 *      @Handler
 *      public void handle(MyCommand2 cmd) {
 *          // do something
 *      }
 *
 *      @Handler
 *      public void handle(MyCommand cmd, Integer a, Integer b) {
 *          // do something
 *      }
 *
 *      public static class ValueProvider {
 *          @Provider
 *          public Integer a() {
 *              return 5;
 *          }
 *
 *          @Provider
 *          public Integer b() {
 *              return 6;
 *          }
 *      }
 * }
 *
 */
public class CommandHandler {

    private Object target;

    private Method method;

    private List<ValueProvider> valueProviders;

    public CommandHandler(Object target, Method method,
                          List<ValueProvider> valueProviders) {
        this.target = target;
        this.method = method;
        this.valueProviders = valueProviders;
    }

    public Object invoke(Object cmd) {
        try {
            return this.method.invoke(target, this.buildParams(cmd));
        } catch (InvocationTargetException ex) {
            if (ex.getCause() instanceof RuntimeException) {
                throw (RuntimeException) ex.getCause();
            }

            throw new IllegalStateException(ex);
        } catch (IllegalAccessException ex) {
            throw new IllegalStateException(ex);
        }
    }

    private Object[] buildParams(Object cmd) {
        return Stream.concat(Arrays.stream(new Object[]{cmd}),
                             valueProviders.stream().map(ValueProvider::invoke))
                     .toArray(Object[]::new);
    }

}
