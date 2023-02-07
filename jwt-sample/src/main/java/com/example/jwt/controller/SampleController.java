package com.example.jwt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

  @GetMapping("/api/sample")
  public ResponseEntity<String> sample() {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body("sample");
  }
}