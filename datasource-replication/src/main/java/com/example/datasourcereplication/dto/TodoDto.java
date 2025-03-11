package com.example.datasourcereplication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class TodoDto {

  @Getter
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  @ToString
  public static class Request {

    private Long id;
    private String title;
    private String description;
    private Boolean completed;

    public static TodoDto.Request of(String title, String description, Boolean completed) {
      return TodoDto.Request.builder()
          .title(title)
          .description(description)
          .completed(completed)
          .build();
    }
  }

  @Getter
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  @ToString
  public static class Response {

    private Long id;
    private String title;
    private String description;
    private Boolean completed;
  }
}
