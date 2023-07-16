package sia.tacocloud.tacos.repos;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.tacos.dto.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient,String> {
}
