package com.example.mail.service;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MailService {

  private final JavaMailSender javaMailSender;

  /**
   * STMP 메일 전송
   */
  public void sendMail(String addressee, String subject, String text) {
    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    simpleMailMessage.setFrom("tychejin12@gmail.com");
    simpleMailMessage.setTo(addressee);
    simpleMailMessage.setSubject(subject);
    simpleMailMessage.setText(text);
    javaMailSender.send(simpleMailMessage);
  }
}
