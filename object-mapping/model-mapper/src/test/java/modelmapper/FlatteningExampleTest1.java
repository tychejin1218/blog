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
public class FlatteningExampleTest1 {

  ModelMapper modelMapper;

  @BeforeEach
  void setup() {
    this.modelMapper = new ModelMapper();
  }

  @DisplayName("Order 객체를 OrderDTO 객체로 변환")
  @Test
  void testOrderToOrderDTO() {

    // Given
    Customer customer = Customer.builder()
        .name("Joe Smith")
        .build();
    Address billingAddress = Address.builder()
        .street("2233 Pike Street")
        .city("Seattle")
        .build();
    Address shippingAddress = Address.builder()
        .street("1234 Market Street")
        .city("San Francisco")
        .build();
    Order order = Order.builder()
        .customer(customer)
        .billingAddress(billingAddress)
        .shippingAddress(shippingAddress)
        .build();

    // When
    // Order 객체를 OrderDTO 객체로 변환
    OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
    log.debug("orderDTO: {}", orderDTO);

    // Then
    assertAll(
        () -> assertEquals(customer.getName(), orderDTO.getCustomerName()),
        () -> assertEquals(shippingAddress.getStreet(), orderDTO.getShippingStreetAddress()),
        () -> assertEquals(shippingAddress.getCity(), orderDTO.getShippingCity()),
        () -> assertEquals(billingAddress.getStreet(), orderDTO.getBillingStreetAddress()),
        () -> assertEquals(billingAddress.getCity(), orderDTO.getBillingCity())
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
    private Address billingAddress;
    private Address shippingAddress;
  }

  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  public static class OrderDTO {

    private String customerName;
    private String shippingStreetAddress;
    private String shippingCity;
    private String billingStreetAddress;
    private String billingCity;
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
    private String city;
  }
}
