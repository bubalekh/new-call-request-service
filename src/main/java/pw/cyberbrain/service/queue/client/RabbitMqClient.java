package pw.cyberbrain.service.queue.client;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

@Component
public class RabbitMqClient implements QueueClient<String>, MessageHandler {
    private final Logger logger = LoggerFactory.getLogger(RabbitMqClient.class);
    private final ConnectionFactory factory = new ConnectionFactory();
    private Channel channel;
    @Value("${rabbitmq.queue.from-frontend}")
    private String FROM_FRONTEND;
    @Value("${rabbitmq.queue.to-notification-service}")
    private String TO_NOTIFICATION_SERVICE;
    @Value("${rabbitmq.host}")
    private String RABBITMQ_HOST;
    private Function<String, Boolean> messageHandler;

    private final DeliverCallback deliverCallback = (consumerTag, delivery) -> {
        String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
        if (messageHandler != null) {
            setMessageAck(delivery, messageHandler.apply(message));
        }
    };

    @PostConstruct
    private void initialize() {
        try {
            factory.setHost(RABBITMQ_HOST);
            Connection connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(TO_NOTIFICATION_SERVICE, false, false, false, null);
            channel.queueDeclare(FROM_FRONTEND, false, false, false, null);
            this.registerConsumeCallback(deliverCallback);
            logger.info("RabbitMQ client initialization complete");
        } catch (IOException | TimeoutException e) {
            logger.error("RabbitMQ client initialization failed");
            throw new RuntimeException(e);
        }
    }

    public void registerConsumeCallback(DeliverCallback callback) {
        try {
            channel.basicConsume(FROM_FRONTEND, false, callback, consumerTag -> {
            });
            logger.info("RabbitMQ Consume callback has been set up");
        } catch (IOException e) {
            logger.error("RabbitMQ Consume callback set up failed!");
            throw new RuntimeException(e);
        }
    }

    public void setMessageAck(Delivery delivery, boolean isHandled) {
        try {
            if (isHandled) {
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
            else {
                channel.basicNack(delivery.getEnvelope().getDeliveryTag(), false, true);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setMessageHandler(Function<String, Boolean> handler) {
        this.messageHandler = handler;
    }

    @Override
    public void sendToQueue(String message) {
        try {
            channel.basicPublish("", TO_NOTIFICATION_SERVICE, null, message.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
