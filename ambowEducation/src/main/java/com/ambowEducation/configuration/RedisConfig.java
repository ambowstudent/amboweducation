package com.ambowEducation.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource("classpath:redis.properties")
public class RedisConfig {

    @Value("${redis.port}")
    private Integer port;

    @Value("${redis.host}")
    private String host;

    @Value("${redis.password}")
    private String password;

    @Value("${redis.database}")
    private Integer database;
}
