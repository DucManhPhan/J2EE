package com.manhpd.springgraphql.exception;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

@Component
public class BasicExceptionHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        ErrorType type = null;

        if (ex instanceof DataIntegrityViolationException) {
            type = ErrorType.BAD_REQUEST;
        } else {
            type = ErrorType.INTERNAL_ERROR;
        }

        return GraphqlErrorBuilder.newError(env)
                .message("Received Message: " + ex.getMessage())
                .errorType(type)
                .build();
    }
}

