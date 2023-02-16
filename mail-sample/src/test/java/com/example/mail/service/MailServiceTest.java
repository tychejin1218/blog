package com.example.mail.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class MailServiceTest {

  @Autowired
  MailService mailService;

  @DisplayName("STMP 메일 전송 테스트")
  @Test
  void testSendMail() {

    String addressee = "";
    String subject = "[테스트] 제목";
    String text = "[테스트] 내용";

    mailService.sendMail(addressee, subject, text);
  }
}