package sia.tacocloud.tacos.converter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import sia.tacocloud.tacos.dto.Ingredient;
import sia.tacocloud.tacos.repos.IngredientRepository;

import java.util.HashMap;
import java.util.Map;

@Component
@Data
@AllArgsConstructor
//Конвертер должен реализовывать интерфейс Converter<S,T>, в SpringBoot конвертер автоматически добавляется в заранее вызыванный бин (ConversionService)
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private IngredientRepository ingredientRepository;

    @Override
    public Ingredient convert(String id) {
        return ingredientRepository.findById(id).orElse(null);
    }
}
