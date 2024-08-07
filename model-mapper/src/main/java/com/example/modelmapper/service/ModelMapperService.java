package com.example.modelmapper.service;

import com.example.modelmapper.domain.Game;
import com.example.modelmapper.dto.GameDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class ModelMapperService {


  /**
   * ModelMapper를 사용해 Game 객체를 GameDto 객체로 변환
   *
   * @param game Game 객체
   * @param modelMapper 매칭 전략이 설정된 ModelMapper 객체
   * @return GameDto 변환된 GameDto 객체
   */
  public GameDto convertGameToGameDto(Game game, ModelMapper modelMapper) {

    GameDto gameDto = modelMapper.map(game, GameDto.class);
    log.debug("MatchingStrategy : [{}], gameDto : [{}]",
        modelMapper.getConfiguration().getMatchingStrategy(), gameDto);

    return gameDto;
  }
}
