
package com.accenture.example.web;

import com.accenture.example.service.PokemonService;
import com.allianz.bmp.claims.solution.offering.service.api.PokemonApi;
import com.allianz.bmp.claims.solution.offering.service.api.model.PokemonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PokemonController implements PokemonApi {

    private final PokemonService pokemonService;

    @Override
    public ResponseEntity<Void> savePokemon(PokemonDto pokemonDto) {
        pokemonService.savePokemon(pokemonDto);
        return ResponseEntity.ok().build();
    }
}
