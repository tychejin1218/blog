package com.example.modelmapper.dto;

import java.time.LocalDateTime;
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
public class Depart3Dto {

  private String deStr3;
  private Integer deInt3;
  private LocalDateTime deDateTime3;
  private Boolean deBoolean3;
}
