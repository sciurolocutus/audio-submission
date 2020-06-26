package com.kanjisoup.config;

import com.kanjisoup.audio.queue.config.AudioQueueConfigurationProperties;
import com.kanjisoup.audio.queue.config.RabbitConnectionConfigurationProperties;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@Configuration
@ComponentScan(basePackages = {
    "com.kanjisoup.audio.submission",
    "com.kanjisoup.audio.event.sdk.client"
})
@EnableConfigurationProperties({AudioQueueConfigurationProperties.class, RabbitConnectionConfigurationProperties.class})
@Slf4j
public class SubmissionApplicationConfig {

    //TODO: see if there is a way to leverage Spring's parsing of spring.rabbitmq properties,
    // as that is much more full-fledged.
    @Bean
    public ConnectionFactory connectionFactory(RabbitConnectionConfigurationProperties connectionConfiguration) throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
        ConnectionFactory factory = new ConnectionFactory();
        if (connectionConfiguration.getUrl() != null) {
            factory.setUri(connectionConfiguration.getUrl());
        }
        if (connectionConfiguration.getUsername() != null) {
            factory.setUsername(connectionConfiguration.getUsername());
        }
        if (connectionConfiguration.getPassword() != null) {
            factory.setPassword(connectionConfiguration.getPassword());
        }

        return factory;
    }
}
