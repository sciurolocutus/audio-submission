package com.kanjisoup.audio.queue.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.rabbitmq")
@Data
public class RabbitConnectionConfigurationProperties {
    private String username;
    private String password;
    private String url;
}
