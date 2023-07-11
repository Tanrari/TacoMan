package sia.tacocloud.tacos.repos;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.tacos.dto.Ingredient;
import sia.tacocloud.tacos.dto.Taco;
import sia.tacocloud.tacos.dto.TacoOrder;

import java.util.Optional;

public interface IngredientRepository extends CrudRepository<Ingredient,String> {
}
