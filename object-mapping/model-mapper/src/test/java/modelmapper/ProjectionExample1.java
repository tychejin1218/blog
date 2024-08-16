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

@Slf4j
public class ProjectionExample1 {

  ModelMapper modelMapper;

  @BeforeEach
  void setup() {
    this.modelMapper = new ModelMapper();
  }

  @DisplayName("OrderInfo를 Order로 매핑")
  @Test
  void testOrderInfoToOrderMapping() {
    // Given
    OrderInfo orderInfo = OrderInfo.builder()
        .customerName("John Doe")
        .streetAddress("456 Elm Street")
        .build();

    // When
    // OrderInfo 객체를 Order 객체로 변환
    Order order = modelMapper.map(orderInfo, Order.class);
    log.debug("order: {}", order);

    // Then
    assertAll(
        () -> assertEquals(orderInfo.getCustomerName(), order.getCustomer().getName()),
        () -> assertEquals(orderInfo.getStreetAddress(), order.getAddress().getStreet())
    );
  }

  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  public static class Order {

    private Customer customer;
    private Address address;
  }

  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  public static class OrderInfo {

    private String customerName;
    private String streetAddress;
  }

  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  public static class Customer {

    private String name;
  }

  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  public static class Address {

    private String street;
  }
}
