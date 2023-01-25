package com.example.rediscluster.properties;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "redis.cluster")
@Component
public class RedisProperties {

    private List<String> nodes;
}
