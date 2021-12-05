package com.manhpd.api;

import com.manhpd.dto.HelloWorldRequestDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/")
public interface Interfaces {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/helloworld")
    public String helloWorld(HelloWorldRequestDto requestDto);

}
