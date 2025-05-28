
package com.accenture.example.mappers;

import com.accenture.example.entity.PokemonEntity;
import com.allianz.bmp.claims.solution.offering.service.api.model.PokemonDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PokemonMapper {

    PokemonEntity toEntity(PokemonDto dto);
}
