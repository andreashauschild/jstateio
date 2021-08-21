package io.jprocess.jpa.mapper;

import io.jprocess.jpa.entity.StateEntity;
import io.jprocess.jpa.entity.StatePropertiesEntity;
import io.jstate.spi.State;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.Map;

@Mapper(config = MapperConfiguration.class, unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class StateMapper extends BaseMapper {

    @Mapping(target = "properties", ignore = true)
    public abstract StateEntity toEntity(State model);

    @AfterMapping
    protected void toEntityAfterMapping(State model, @MappingTarget StateEntity entity) {
        for (Map.Entry<String, String> entry : model.getProperties().entrySet()) {
            StatePropertiesEntity prop = new StatePropertiesEntity(entry.getKey(), entry.getValue());
            prop.setState(entity);
            entity.getProperties().add(prop);
        }
    }


    @Mapping(target = "properties", ignore = true)
    public abstract State toModel(StateEntity entity);

    @AfterMapping
    protected void toModelAfterMapping(StateEntity entity, @MappingTarget State model) {
        for (StatePropertiesEntity props : entity.getProperties()) {
            model.getProperties().put(props.getKey(), props.getValue());
        }
    }

}
