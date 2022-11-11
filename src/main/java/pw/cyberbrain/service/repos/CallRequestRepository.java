package pw.cyberbrain.service.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pw.cyberbrain.service.domain.CallRequestDao;
@Repository
public interface CallRequestRepository extends CrudRepository<CallRequestDao, Long> {
}
