package io.jprocess.rest;

import io.jprocess.rest.model.CreateProcess;
import io.jstate.spi.JProcessService;
import io.jstate.spi.ProcessInstance;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/jprocess-service")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class JProcessResource {

    @Inject
    JProcessService service;

    @POST
    @Operation(operationId = "createProcessInstance")
    @Path("process-instance")
    public ProcessInstance create(CreateProcess obj) {
        return this.service.create(obj.getTemplateId(), obj.getPropertiesAsMap());
    }

    @GET
    @Operation(operationId = "getProcessInstance")
    @Path("process-instance/{id}")
    public ProcessInstance get(@PathParam("id") String id) {
        return this.service.get(id);
    }


    @GET
    @Operation(operationId = "executeSync")
    @Path("process-instance/execute-sync/{id}")
    public ProcessInstance executeSync(@PathParam("id") String id) {
        return this.service.executeSync(id);
    }


}
