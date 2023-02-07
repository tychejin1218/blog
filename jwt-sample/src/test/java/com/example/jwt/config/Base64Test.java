package com.example.jwt.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class Base64Test {

  @DisplayName("Base64 암호화 및 복호화 테스트")
  @Test
  public void testBase64Test() {

    // Given
    String originalStr = "spring_security_with_json_web_token_secret_key";
    byte[] originalBytes = originalStr.getBytes();
    // 256bit -> 32byte
    log.debug("originalBytes.length:{}", originalBytes.length);

    // When
    Encoder encoder = Base64.getEncoder();
    byte[] encodedBytes = encoder.encode(originalBytes);
    String encodedStr = new String(encodedBytes);

    Decoder decoder = Base64.getDecoder();
    byte[] decodedBytes = decoder.decode(encodedBytes);
    String decodedStr = new String(decodedBytes);

    // Then
    log.debug("encodedBytes:[{}]", encodedStr);
    log.debug("decodedBytes:[{}]", decodedStr);
    assertEquals(originalStr, decodedStr);
  }
}