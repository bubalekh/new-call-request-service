package pw.cyberbrain.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan("pw.cyberbrain.service")
public class NewRequestService {
    public static void main(String[] args) {
        SpringApplication.run(NewRequestService.class, args);
    }
}
