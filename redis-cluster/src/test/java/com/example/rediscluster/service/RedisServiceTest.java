package com.example.rediscluster.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class RedisServiceTest {

  @Autowired
  private RedisService redisService;

  @Test
  void testSetRedis()  {

    // Given
    String requestKey = "test_key";
    String requestValue = "test_value";

    // When
    redisService.setRedis(requestKey, requestValue);

    String savedValue = redisService.getRedis(requestKey);

    // Then
    log.info("requestValue:[{}]", requestValue);
    log.info("savedValue:[{}]", savedValue);
    assertEquals(requestValue, savedValue);
  }
}