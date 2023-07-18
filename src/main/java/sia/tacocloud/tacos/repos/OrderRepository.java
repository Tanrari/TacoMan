package sia.tacocloud.tacos.repos;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.tacos.dto.TacoOrder;
import sia.tacocloud.tacos.dto.User;

import javax.persistence.criteria.Order;
import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder,Long> {
    List<Order> findByUserOrderByPlacedAtDesc (User user, Pageable pageable);
}
