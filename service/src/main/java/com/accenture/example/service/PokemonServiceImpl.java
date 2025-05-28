package com.accenture.example.service;

import com.accenture.example.mappers.PokemonMapper;
import com.accenture.example.repository.PokemonRepository;
import com.allianz.bmp.claims.solution.offering.service.api.model.PokemonDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class PokemonServiceImpl implements PokemonService{
    private final PokemonRepository repository;
    private final PokemonMapper mapper;

    @Override
    public void savePokemon(PokemonDto pokemonDto) {
        log.info("I do nothing (for now)");
        /* TODO the following code only works once the mapper is ready
        PokemonEntity entity = repository.save(mapper.toEntity(pokemonDto));
        log.info("Created a new pokemon with id {}", entity.getId());
        */
    }
}
