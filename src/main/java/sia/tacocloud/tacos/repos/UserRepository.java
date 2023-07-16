package sia.tacocloud.tacos.repos;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.tacos.dto.User;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String username);
}
