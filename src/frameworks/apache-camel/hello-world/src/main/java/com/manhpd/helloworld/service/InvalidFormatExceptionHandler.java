package com.manhpd.helloworld.service;

import com.manhpd.helloworld.dto.ResponseDto;
import com.manhpd.helloworld.utils.Constants;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class InvalidFormatExceptionHandler implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Throwable caused = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Throwable.class);
        if (Objects.nonNull(caused)) {
            System.out.println("InvalidFormatException happen");
            System.out.println("Exception's content: " + caused.getMessage());

            this.setResponseBody(exchange);
        }
    }

    private void setResponseBody(Exchange exchange) {
        ResponseDto response = this.buildErrorResponse();
        exchange.getOut().setBody(response);
    }

    private ResponseDto buildErrorResponse() {
        return new ResponseDto(Constants.BAD_REQUEST_STATUS,
                Constants.INVALID_FORMAT_ERROR_MESSAGE);
    }
}
