package com.manhpd.ws;

import com.manhpd.dto.EmployeeDto;
import com.manhpd.dto.UpdateEmployeeData;
import com.manhpd.dto.UpdateEmployeeStatus;
import com.manhpd.entity.EmployeeEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
public interface EmployeeController {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get-employees")
    List<EmployeeDto> getEmployees();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/update-employee-id/{id}")
    UpdateEmployeeStatus updateEmployeeWithId(@PathParam("id") int id, UpdateEmployeeData data);

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/ping")
    public String ping();

}
