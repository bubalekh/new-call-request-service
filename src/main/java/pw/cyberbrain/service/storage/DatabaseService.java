package pw.cyberbrain.service.storage;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import pw.cyberbrain.service.domain.CallRequestDao;
import pw.cyberbrain.service.dto.CallRequestDto;
import pw.cyberbrain.service.repos.CallRequestRepository;

import java.util.Objects;

@Service
@Order(1)
public class DatabaseService implements StorageService {

    @Autowired
    private CallRequestRepository repository;

    private final ModelMapper mapper = new ModelMapper();

    public DatabaseService() {
        mapper.typeMap(CallRequestDto.class, CallRequestDao.class).addMappings(m -> m.skip(CallRequestDao::setId));
    }

    @Override
    public boolean handleNewRequest(CallRequestDto request) {
        CallRequestDao callRequestDao = mapper.map(request, CallRequestDao.class);
        return Objects.equals(repository.save(callRequestDao).getUserId(), callRequestDao.getUserId());
    }
}
