package io.jprocess.jpa.mapper;

import io.jprocess.jpa.entity.StateEntity;
import io.jstate.spi.State;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

@Mapper(config = MapperConfiguration.class, unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class BaseMapper {
    public static String map(UUID value) {
        if (value != null) {
            return value.toString();
        }
        return null;
    }

    public static UUID map(String id) {
        if (id != null) {
            return UUID.fromString(id);
        }
        return null;
    }

}
