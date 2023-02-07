package com.example.jwt.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

  @NotNull
  @Size(min = 3, max = 50)
  private String name;

  @NotNull
  @Size(min = 3, max = 100)
  private String password;
}