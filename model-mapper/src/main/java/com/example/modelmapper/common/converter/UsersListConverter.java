package com.example.modelmapper.common.converter;

import com.example.modelmapper.domain.User;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.AbstractConverter;

public class UsersListConverter extends AbstractConverter<List<User>, List<String>> {

  /**
   * User 객체의 리스트를 User의 username 문자열 리스트로 변환
   *
   * @param users List&lt;User&gt;
   * @return List&lt;String&gt;
   */
  @Override
  protected List<String> convert(List<User> users) {
    return users
        .stream()
        .map(User::getUserName)
        .collect(Collectors.toList());
  }
}
