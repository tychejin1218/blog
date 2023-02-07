package com.example.jwt.controller;

import com.example.jwt.config.jwt.JwtFilter;
import com.example.jwt.config.jwt.TokenProvider;
import com.example.jwt.dto.LoginDto;
import com.example.jwt.dto.TokenDto;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
public class AuthController {

  private final TokenProvider tokenProvider;
  private final AuthenticationManagerBuilder authenticationManagerBuilder;

  @PostMapping("/api/authenticate")
  public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto) {

    String jwt = "";

    try {

      UsernamePasswordAuthenticationToken authenticationToken =
          new UsernamePasswordAuthenticationToken(loginDto.getName(), loginDto.getPassword());

      Authentication authentication = authenticationManagerBuilder.getObject()
          .authenticate(authenticationToken);

      SecurityContextHolder.getContext().setAuthentication(authentication);

      jwt = tokenProvider.createToken(authentication);

    } catch (Exception e) {
      log.error("authorize : [{}]", loginDto.toString(), e);
    }

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

    return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
  }
}