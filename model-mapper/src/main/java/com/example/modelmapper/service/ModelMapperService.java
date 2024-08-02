package com.example.modelmapper.service;

import com.example.modelmapper.dto.Depart1Dto;
import com.example.modelmapper.dto.Depart3Dto;
import com.example.modelmapper.dto.Destination1Dto;
import com.example.modelmapper.dto.DestinationDto;
import com.example.modelmapper.dto.SourceDepart;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class ModelMapperService {

  private final ModelMapper modelMapperLoose;
  private final ModelMapper modelMapperStandard;
  private final ModelMapper modelMapperStrict;

  public void convertModelMapper() {

    Depart1Dto depart1DTO = Depart1Dto.builder()
        .name("name1")
        .age(10)
        .regDt(LocalDateTime.now())
        .isYn(true)
        .build();

    DestinationDto destinationLoose = modelMapperLoose.map(depart1DTO, DestinationDto.class);
    log.debug("Loose - destinationDTO : {}", destinationLoose);

    DestinationDto destinationStandard = modelMapperStandard.map(depart1DTO, DestinationDto.class);
    log.debug("Standard - destinationDTO : {}", destinationStandard);

    DestinationDto destinationStrict = modelMapperStrict.map(depart1DTO, DestinationDto.class);
    log.debug("Strict - destinationDTO : {}", destinationStrict);
  }


  public void convertModelMapper02() {

    Depart1Dto depart1 = Depart1Dto.builder()
        .name("name1")
        .age(10)
        .regDt(LocalDateTime.now())
        .isYn(true)
        .build();

    Depart3Dto depart3 = Depart3Dto.builder()
        .deStr3("name3")
        .deInt3(30)
        .deDateTime3(LocalDateTime.now())
        .deBoolean3(false)
        .build();

    SourceDepart sourceDepart = SourceDepart.builder()
        .name("name")
        .age(50)
        .hpNo("010-1234-5678")
        .isTrue(true)
        .depart1Dto(depart1)
        .depart3Dto(depart3)
        .build();

    Destination1Dto destinationLoose = modelMapperLoose.map(sourceDepart, Destination1Dto.class);
    log.debug("Loose - destinationDTO : {}", destinationLoose);

    Destination1Dto destinationStandard = modelMapperStandard.map(sourceDepart, Destination1Dto.class);
    log.debug("Standard - destinationDTO : {}", destinationStandard);

    Destination1Dto destinationStrict = modelMapperStrict.map(sourceDepart, Destination1Dto.class);
    log.debug("Strict - destinationDTO : {}", destinationStrict);
  }
}
