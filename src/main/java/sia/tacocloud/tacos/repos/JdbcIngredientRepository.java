package sia.tacocloud.tacos.repos;
import jdk.internal.org.objectweb.asm.Type;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sia.tacocloud.tacos.dto.Ingredient;
import sia.tacocloud.tacos.dto.IngredientRef;
import sia.tacocloud.tacos.dto.Taco;
import sia.tacocloud.tacos.dto.TacoOrder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class JdbcIngredientRepository implements IngredientRepository {


    private JdbcTemplate jdbcTemplate;


    @Override
    public Iterable<Ingredient> findAll() {
        return jdbcTemplate.query("select id,name, type from ingredient",
                this::mapRowToIngredient);
    }

    private Ingredient  mapRowToIngredient(ResultSet row, int rowNum) throws SQLException {
        return new Ingredient(row.getString("id"),
                row.getString("name"),
                Ingredient.Type.valueOf(row.getString("type")));
    }

    @Override
    public Optional<Ingredient> findById(String id) {
        List<Ingredient> results = jdbcTemplate.query("select id, name , type from Ingredient where id=?",
                this::mapRowToIngredient,id);
        return results.size()==0?Optional.empty():Optional.of(results.get(0));
    }

    @Override
    public Taco save(Taco order) {
        return null;
    }



    }

