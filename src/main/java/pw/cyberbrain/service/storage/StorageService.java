package pw.cyberbrain.service.storage;

import pw.cyberbrain.service.dto.CallRequestDto;

public interface StorageService {
    boolean handleNewRequest(CallRequestDto request);
}
