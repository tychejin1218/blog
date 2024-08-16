package modelmapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;

@Slf4j
public class FlatteningExampleTest2 {

  ModelMapper modelMapper;

  @BeforeEach
  void setup() {
    this.modelMapper = new ModelMapper();
  }

  @DisplayName("PropertyMap 사용하여 명시적 매핑")
  @Test
  void testUsingPropertyMap() {

    // Given
    Address address = Address.builder()
        .street("123 Main Street")
        .city("Springfield")
        .build();

    Person person = Person.builder()
        .address(address)
        .build();

    // PropertyMap을 사용하여 명시적 매핑 설정
    PropertyMap<Person, PersonDTO> personMap = new PropertyMap<>() {
      protected void configure() {
        // Address 객체의 street 속성을 PersonDTO 객체의 street 속성에 매핑
        map().setStreet(source.getAddress().getStreet());
        // Address 객체의 city 속성을 PersonDTO 객체의 city 속성에 매핑
        map(source.getAddress().getCity(), destination.getCity());
      }
    };

    // PropertyMap 추가
    modelMapper.addMappings(personMap);

    // When
    // Person 객체를 PersonDTO 객체로 변환
    PersonDTO personDTO = modelMapper.map(person, PersonDTO.class);
    log.debug("personDTO: {}", personDTO);

    // Then
    assertAll(
        () -> assertEquals(address.getStreet(), personDTO.getStreet()),
        () -> assertEquals(address.getCity(), personDTO.getCity())
    );
  }

  @DisplayName("Loose Matching Strategy 사용")
  @Test
  void testUsingLooseMatchingStrategy() {

    // Given
    Address address = Address.builder()
        .street("123 Main Street")
        .city("Springfield")
        .build();

    Person person = Person.builder()
        .address(address)
        .build();

    // Loose Matching Strategy 설정
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

    // When
    // Person 객체를 PersonDTO 객체로 변환
    PersonDTO personDTO = modelMapper.map(person, PersonDTO.class);
    log.debug("personDTO: {}", personDTO);

    // Then
    assertAll(
        () -> assertEquals(address.getStreet(), personDTO.getStreet()),
        () -> assertEquals(address.getCity(), personDTO.getCity())
    );
  }

  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  public static class Person {

    private Address address;
  }

  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  public static class Address {

    private String street;
    String city;
  }

  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  public static class PersonDTO {

    private String street;
    String city;
  }
}
