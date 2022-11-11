package pw.cyberbrain.service.queue.client;

import java.util.function.Function;

public interface MessageHandler {
     void setMessageHandler(Function<String, Boolean> handler);
}
