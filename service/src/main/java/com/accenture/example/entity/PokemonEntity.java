
package com.accenture.example.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pokemon")
@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class PokemonEntity {
    @Id
    private String id;
    private String pokeName;
    private String type;
    private int level;
}
