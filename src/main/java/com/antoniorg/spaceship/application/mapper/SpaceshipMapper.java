package com.antoniorg.spaceship.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.antoniorg.spaceship.application.dto.SpaceshipCreateRequest;
import com.antoniorg.spaceship.application.dto.SpaceshipResponse;
import com.antoniorg.spaceship.application.dto.SpaceshipUpdateRequest;
import com.antoniorg.spaceship.domain.model.SpaceshipModel;

@Mapper
public interface SpaceshipMapper {
    SpaceshipMapper INSTANCE = Mappers.getMapper(SpaceshipMapper.class);

    SpaceshipResponse fromDomain(SpaceshipModel spaceship);
    
    SpaceshipModel fromUpdateRequest(Long id, SpaceshipUpdateRequest req);
    
    @Mapping(target = "id", ignore = true)
    SpaceshipModel fromCreateRequest(SpaceshipCreateRequest req);
}
