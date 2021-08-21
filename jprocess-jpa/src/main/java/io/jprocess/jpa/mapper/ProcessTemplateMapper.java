package io.jprocess.jpa.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jprocess.jpa.entity.ProcessTemplateEntity;
import io.jstate.model.configuration.ProcessTemplate;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

@Mapper(imports = UUID.class,config = MapperConfiguration.class, unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class ProcessTemplateMapper {


    public ProcessTemplateEntity toEntity(ProcessTemplate model) {
        if (model != null) {
            try {
                String template = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(model);
                return new ProcessTemplateEntity().setTemplate(template);
            } catch (JsonProcessingException e) {
                // TODO exception
                throw new IllegalArgumentException(e);
            }
        } else {
            return null;
        }
    }


    public ProcessTemplate toModel(ProcessTemplateEntity entity) {
        if (entity != null) {
            try {
                ProcessTemplate processTemplate = new ObjectMapper().readValue(entity.getTemplate(), ProcessTemplate.class);
                processTemplate.setId(entity.getId().toString());
                return processTemplate;
            } catch (JsonProcessingException e) {
                // TODO exception
                throw new IllegalArgumentException(e);
            }
        } else {
            return null;
        }
    }


}
