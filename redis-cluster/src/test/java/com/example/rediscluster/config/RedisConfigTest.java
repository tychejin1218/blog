package com.example.rediscluster.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisConfigTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testDataHandling() {

        redisTemplate.getConnectionFactory().getConnection().info().toString();

        String key = "yeoseong";
        String value = "yoon";
        redisTemplate.opsForValue().set(key, value);
        String returnValue = (String) redisTemplate.opsForValue().get(key);

        System.out.println(value);
    }

}