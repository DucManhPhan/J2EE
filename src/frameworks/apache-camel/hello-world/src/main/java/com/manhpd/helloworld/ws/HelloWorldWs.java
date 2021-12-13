package com.manhpd.helloworld.ws;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.manhpd.helloworld.dto.RequestDto;
import com.manhpd.helloworld.service.BeanValidationService;
import com.manhpd.helloworld.service.InvalidFormatExceptionHandler;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.ValidationException;
import javax.ws.rs.core.MediaType;

@Component
public class HelloWorldWs extends RouteBuilder {

    @Value("${api.path}")
    private String contextPath;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private InvalidFormatExceptionHandler parseExceptionHandler;

    @Autowired
    private BeanValidationService beanValidationService;

    @Override
    public void configure() throws Exception {
//        onException(InvalidFormatException.class)
//                .process(this.parseExceptionHandler)
//                .handled(true)
//                .stop();

//        CamelContext context = new DefaultCamelContext();
        restConfiguration().contextPath(this.contextPath)
                .port(this.serverPort)
                .enableCORS(true)
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "Test REST API")
                .apiProperty("api.version", "v1")
                .apiProperty("cors", "true")
                .apiContextRouteId("doc-api")
                .component("servlet")
                // By default the binding mode is off, meaning there is no automatic
                // binding happening for incoming and outgoing messages.
                // You may want to use binding if you develop POJOs that maps to
                // your REST services request and response types.
                .bindingMode(RestBindingMode.json)
                .dataFormatProperty("prettyPrint", "true");

        // define APIs
        this.getContentApi();
    }

    private void getContentApi() {
        rest("/api/")
                .description("Test REST API")
                .id("api-route")
                .post("/bean")
                .produces(MediaType.APPLICATION_JSON)
                .consumes(MediaType.APPLICATION_JSON)
                .bindingMode(RestBindingMode.auto)
                .type(RequestDto.class)
                .enableCORS(true)
                // .outType(OutBean.class)
                .to("direct:helloWorld");

        from("direct:helloWorld")
                .routeId("direct-route")
                .doTry()
                    .bean(this.beanValidationService, "validate")
                    .log("validate request")
//                    .tracing()
                    .log(">>> ${body.id}")
                    .log(">>> ${body.name}")
                    // .transform().simple("blue ${in.body.name}")
                    .process( exchange -> {
                        RequestDto dto = exchange.getIn().getBody(RequestDto.class);
                        dto.setName("Hello " + dto.getName());
                        exchange.getIn().setBody(dto);
                    })
                    .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201))
//                .doCatch(InvalidFormatException.class, ValidationException.class)
                .doCatch(Exception.class)
                    .log("Happen invalid data in request")
                    .bean(this.beanValidationService, "getErrorValidation")
                .doFinally()
                    .log("Finally")
                .endDoTry();
    }

}
