package io.jprocess.mapper;

import io.jprocess.hibernate.entities.BaseEntity;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingInheritanceStrategy;

@MapperConfig(componentModel = "cdi", mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG)
public abstract class MapperConfiguration {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModified", ignore = true)
    @Mapping(target = "created", ignore = true)
    public abstract BaseEntity mapBase(Object model);
}
