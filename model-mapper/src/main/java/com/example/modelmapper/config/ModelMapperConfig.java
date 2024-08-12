package com.example.modelmapper.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

  /**
   * ModelMapper 빈을 설정
   *
   * @return ModelMapper 객체를 반환
   */
  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration()
        .setFieldMatchingEnabled(true)
        .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
    return modelMapper;
  }

  /**
   * 'LOOSE' 매칭 전략을 적용한 ModelMapper 빈을 설정
   *
   * @return ModelMapper 객체를 반환
   */
  @Bean
  public ModelMapper modelMapperLoose() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.LOOSE)
        .setFieldMatchingEnabled(true)
        .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
    return modelMapper;
  }

  /**
   * 'STANDARD' 매칭 전략을 적용한 ModelMapper 빈을 설정
   *
   * @return ModelMapper 객체를 반환
   */
  @Bean
  public ModelMapper modelMapperStandard() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STANDARD)
        .setFieldMatchingEnabled(true)
        .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
    return modelMapper;
  }

  /**
   * 'STRICT' 매칭 전략을 적용한 ModelMapper 빈을 설정
   *
   * @return ModelMapper 객체를 반환
   */
  @Bean
  public ModelMapper modelMapperStrict() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STRICT)
        .setFieldMatchingEnabled(true)
        .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
    return modelMapper;
  }
}
