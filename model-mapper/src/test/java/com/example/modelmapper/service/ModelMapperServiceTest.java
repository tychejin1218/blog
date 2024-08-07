package com.example.modelmapper.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.modelmapper.domain.Game;
import com.example.modelmapper.dto.GameDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class ModelMapperServiceTest {

  @Autowired
  ModelMapper modelMapperLoose;

  @Autowired
  ModelMapper modelMapperStandard;

  @Autowired
  ModelMapper modelMapperStrict;

  @Autowired
  ModelMapperService modelMapperService;

  @Order(1)
  @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
  @DisplayName("convertGameToGameDto_ModelMapper를 사용해 Game 객체를 GameDto 객체로 변환")
  @Nested
  class TestConvertGameToGameDto {

    @Order(1)
    @DisplayName("'LOOSE' 매칭 전략을 적용한 ModelMapper로 Game 객체를 GameDto 객체로 변환")
    @Test
    public void testLooseConvertGameToGameDto() {

      // Given
      Game game = Game.builder()
          .id(1L)
          .name("Game_1")
          .build();

      // When
      GameDto gameDtoLoose = modelMapperService.convertGameToGameDto(game, modelMapperLoose);

      // Then
      assertAll(
          () -> assertEquals(game.getId(), gameDtoLoose.getId()),
          () -> assertEquals(game.getName(), gameDtoLoose.getName())
      );
    }

    @Order(2)
    @DisplayName("'STANDARD' 매칭 전략을 적용한 ModelMapper로 Game 객체를 GameDto 객체로 변환")
    @Test
    public void testStandardConvertGameToGameDto() {

      // Given
      Game game = Game.builder()
          .id(1L)
          .name("Game_1")
          .build();

      // When
      GameDto gameDtoStandard = modelMapperService.convertGameToGameDto(game, modelMapperStandard);

      // Then
      assertAll(
          () -> assertEquals(game.getId(), gameDtoStandard.getId()),
          () -> assertEquals(game.getName(), gameDtoStandard.getName())
      );
    }

    @Order(3)
    @DisplayName("'STRICT' 매칭 전략을 적용한 ModelMapper로 Game 객체를 GameDto 객체로 변환")
    @Test
    public void testStrictConvertGameToGameDto() {

      // Given
      Game game = Game.builder()
          .id(1L)
          .name("Game_1")
          .build();

      // When
      GameDto gameDtoStrict = modelMapperService.convertGameToGameDto(game, modelMapperStrict);

      // Then
      assertAll(
          () -> assertEquals(game.getId(), gameDtoStrict.getId()),
          () -> assertEquals(game.getName(), gameDtoStrict.getName())
      );
    }
  }
}
