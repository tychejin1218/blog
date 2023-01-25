package com.example.rediscluster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

  @Autowired
  StringRedisTemplate redisTemplate;

  public String setRedis(String key, String value) {
    ValueOperations<String, String> ops = redisTemplate.opsForValue();
    ops.set(key, value);
    return "saved";
  }

  public String getRedis(String key) {
    ValueOperations<String, String> ops = redisTemplate.opsForValue();
    String value = ops.get(key);
    return value;
  }
}