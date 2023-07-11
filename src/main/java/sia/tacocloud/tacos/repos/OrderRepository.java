package sia.tacocloud.tacos.repos;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.tacos.dto.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder,Long> {

}
