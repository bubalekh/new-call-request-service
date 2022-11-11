package pw.cyberbrain.service.queue.client;

public interface QueueClient<T> {
    void sendToQueue(T message);
}
