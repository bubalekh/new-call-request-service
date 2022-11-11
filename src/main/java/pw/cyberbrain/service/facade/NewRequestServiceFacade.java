package pw.cyberbrain.service.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pw.cyberbrain.service.queue.client.MessageHandler;
import pw.cyberbrain.service.storage.StorageService;

@Component
public class NewRequestServiceFacade {

    @Autowired
    public NewRequestServiceFacade(MessageHandler handler, StorageService service) {
        handler.setMessageHandler(service::handleNewRequest);
    }
}
