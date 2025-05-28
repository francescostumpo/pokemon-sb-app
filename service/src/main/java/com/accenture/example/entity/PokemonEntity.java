
package com.accenture.example.entity;

import lombok.*;
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
    private String name;
    private String type;
    private int level;
}
