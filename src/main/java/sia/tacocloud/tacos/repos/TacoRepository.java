package sia.tacocloud.tacos.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import sia.tacocloud.tacos.dto.Taco;

import java.net.URLConnection;

public interface TacoRepository extends PagingAndSortingRepository {


}
