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
public class ProjectionExample2 {

  ModelMapper modelMapper;

  @BeforeEach
  void setup() {
    this.modelMapper = new ModelMapper();
  }

  @DisplayName("PropertyMap 사용하여 OrderDTO를 Order로 매핑")
  @Test
  void testOrderDTOToOrderMappingUsingPropertyMap() {
    // Given
    OrderDTO orderDTO = OrderDTO.builder()
        .street("789 Pine Street")
        .city("Metropolis")
        .build();

    // PropertyMap을 사용하여 명시적 매핑 설정
    PropertyMap<OrderDTO, Order> orderMap = new PropertyMap<>() {
      protected void configure() {
        // OrderDTO의 street 속성을 Order의 address.street 속성에 매핑
        map().getAddress().setStreet(source.getStreet());
        // OrderDTO의 city 속성을 Order의 address.city 속성에 매핑
        map().address.setCity(source.getCity());
      }
    };

    // PropertyMap을 ModelMapper에 추가
    modelMapper.addMappings(orderMap);

    // When
    // OrderDTO 객체를 Order 객체로 변환
    Order order = modelMapper.map(orderDTO, Order.class);
    log.debug("order: {}", order);

    // Then
    assertAll(
        () -> assertEquals(orderDTO.getStreet(), order.getAddress().getStreet()),
        () -> assertEquals(orderDTO.getCity(), order.getAddress().getCity())
    );
  }

  @DisplayName("Loose Matching Strategy 사용하여 OrderDTO를 Order로 매핑")
  @Test
  void testOrderDTOToOrderMappingUsingLooseMatchingStrategy() {
    // Given
    OrderDTO orderDTO = OrderDTO.builder()
        .street("789 Pine Street")
        .city("Metropolis")
        .build();

    // Loose Matching Strategy 설정
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

    // When
    // OrderDTO 객체를 Order 객체로 변환
    Order order = modelMapper.map(orderDTO, Order.class);
    log.debug("order: {}", order);

    // Then
    assertAll(
        () -> assertEquals(orderDTO.getStreet(), order.getAddress().getStreet()),
        () -> assertEquals(orderDTO.getCity(), order.getAddress().getCity())
    );
  }

  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  public static class Order {
    private Address address;
  }

  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  public static class OrderDTO {
    private String street;
    private String city;
  }

  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  public static class Address {
    private String street;
    private String city;
  }
}
