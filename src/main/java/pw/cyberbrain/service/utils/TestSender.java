package pw.cyberbrain.service.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import pw.cyberbrain.service.dto.CallRequestDto;

import java.nio.charset.StandardCharsets;

public class TestSender {
    private final static String QUEUE_NAME = "test-queue";

    public static void main(String[] argv) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            CallRequestDto dto = new CallRequestDto();
            dto.setName("Test Name");
            dto.setPhone("+79876543210");
            dto.setDate("Test Date");
            dto.setUserId(1L);
            dto.setTime("Test time");
            String message = mapper.writeValueAsString(dto);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + dto + "'");
        }
    }
}
