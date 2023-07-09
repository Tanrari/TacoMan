package sia.tacocloud.tacos.repos;

import sia.tacocloud.tacos.dto.Ingredient;
import sia.tacocloud.tacos.dto.Taco;
import sia.tacocloud.tacos.dto.TacoOrder;

import java.util.Optional;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();
    Optional<Ingredient> findById(String id);
    TacoOrder save(TacoOrder order);

}
