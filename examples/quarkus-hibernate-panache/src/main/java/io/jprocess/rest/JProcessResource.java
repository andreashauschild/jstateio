package io.jprocess.rest;

import io.jstate.model.configuration.ProcessTemplate;
import io.jstate.spi.ProcessRepository;
import io.jstate.spi.ProcessTemplateRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class JProcessResource {

    @Inject
    ProcessRepository processRepository;

    @Inject
    ProcessTemplateRepository processTemplateRepository;

    @POST
    @Operation(operationId = "saveProcessTemplate")
    @Path("process-template")
    public ProcessTemplate saveProcessTemplate(ProcessTemplate template) {
        return this.processTemplateRepository.createProcessTemplate(template);
    }
}
