package pw.cyberbrain.service.queue.client;

import pw.cyberbrain.service.dto.CallRequestDto;

import java.util.function.Function;

public interface MessageHandler {
     void setMessageHandler(Function<CallRequestDto, Boolean> handler);
}
