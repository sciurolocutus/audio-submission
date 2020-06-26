package com.kanjisoup.audio.queue.config;

import com.kanjisoup.audio.event.sdk.config.AudioQueueClientConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

//TODO: extract this to shared SDK
@ConfigurationProperties(prefix = "com.kanjisoup.audio.queue")
@Data
public class AudioQueueConfigurationProperties implements AudioQueueClientConfig {
    private String exchange;
    private String exchangeType;
    private String routingKey;
    private String queueName;
}
