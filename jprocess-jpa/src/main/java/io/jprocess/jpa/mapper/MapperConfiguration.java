package io.jprocess.jpa.mapper;

import io.jprocess.jpa.entity.BaseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingInheritanceStrategy;

import java.util.UUID;

@MapperConfig(mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG)
public abstract class MapperConfiguration {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModified", ignore = true)
    @Mapping(target = "created", ignore = true)
    public abstract BaseEntity mapBase(Object model);

}
