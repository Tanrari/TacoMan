package sia.tacocloud.tacos.dto;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Data
@RequiredArgsConstructor
@Entity
//@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class Ingredient {
    @Id
    private final String id;
    private final String name;
    @Enumerated
    private final Type type;

    public enum Type{
        WRAP,PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
