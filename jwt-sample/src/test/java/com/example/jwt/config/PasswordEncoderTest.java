package com.example.jwt.config;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@SpringBootTest
public class PasswordEncoderTest {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @DisplayName("비밀번호 단방향 암호화 확인")
  @Test
  void testPasswordEncoder() {

    // Given
    String rawPassword = "password1!";

    // When
    String encodedPassword = passwordEncoder.encode(rawPassword);

    // Then
    log.debug("encodedPassword:[{}]", encodedPassword);
    assertAll(
        () -> assertNotEquals(rawPassword, encodedPassword),
        () -> assertTrue(passwordEncoder.matches(rawPassword, encodedPassword))
    );
  }
}
