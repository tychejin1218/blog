package com.example.modelmapper.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SourceDepart {

  private String name;
  private int age;
  private String hpNo;
  private boolean isTrue;
  private Depart1Dto depart1Dto;
  private Depart3Dto depart3Dto;
}
