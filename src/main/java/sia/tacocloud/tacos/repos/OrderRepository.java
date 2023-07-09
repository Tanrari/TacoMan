package sia.tacocloud.tacos.repos;

import sia.tacocloud.tacos.dto.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder order);
}
