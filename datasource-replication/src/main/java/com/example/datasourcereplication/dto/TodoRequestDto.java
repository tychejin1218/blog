package com.example.datasourcereplication.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TodoRequestDto {

  private String title;
  private String description;
  private Boolean completed;
}
