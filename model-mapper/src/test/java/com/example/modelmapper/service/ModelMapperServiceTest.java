package com.example.modelmapper.service;

import static org.junit.jupiter.api.Assertions.*;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class ModelMapperServiceTest {


  @Autowired
  ModelMapperService modelMapperService;

  @Test
  public void testConvertModelMapper(){

    // Given

    // When
    modelMapperService.convertModelMapper();

    // Then
  }

  @Test
  public void testConvertModelMapper02(){

    // Given

    // When
    modelMapperService.convertModelMapper02();

    // Then
  }
}
