
package com.accenture.example.repository;

import com.accenture.example.entity.PokemonEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PokemonRepository extends MongoRepository<PokemonEntity, String> {}
