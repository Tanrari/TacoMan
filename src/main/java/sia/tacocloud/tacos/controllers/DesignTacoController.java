package sia.tacocloud.tacos.controllers;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.tacos.dto.Ingredient;
import sia.tacocloud.tacos.dto.Ingredient.Type;
import sia.tacocloud.tacos.dto.Taco;
import sia.tacocloud.tacos.dto.TacoOrder;
import sia.tacocloud.tacos.repos.IngredientRepository;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")  //Определяет тип запросов , которые обрабатывает этот контроллер
@SessionAttributes("tacoOrder") //Указывает на то , что класс должен поддерживаться на уровне сеанса
@Data
public class DesignTacoController {

    private final IngredientRepository repository;

    @ModelAttribute //аннотация, связывающая параметр метода или возвращаемое значение метода
                   // с атрибутом модели , которая будет использоваться при выводе страницы. Такая же аннотация перед параметром,
                 // указывает на то, что должен использоваться объект , помеченный аннотацией @ModelAttribute
    public void addIngredientsToModel(Model model){
    Iterable<Ingredient> ingredients = repository.findAll();
    Type[] types = Ingredient.Type.values();
    for (Type type: types){
        model.addAttribute(type.toString().toLowerCase(),
                filterByType((List<Ingredient>) ingredients,type));
    }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order(){
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(){
        return "design";
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type){
        return ingredients.stream().filter(x->x.getType().equals(type)).collect(Collectors.toList());
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder){
        if (errors.hasErrors()){
            return "design";
        }
        tacoOrder.addTaco(taco);
        log.info("Processing taco:{}",taco );
        return "redirect:/orders/current";
    }


}
