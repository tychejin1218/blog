package mapstruct;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import lombok.extern.slf4j.Slf4j;
import mapstruct.dto.OrderDto;
import mapstruct.entity.Address;
import mapstruct.entity.Customer;
import mapstruct.entity.Order;
import mapstruct.mapper.OrderMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class FlatteningExampleTest {

  @DisplayName("Order 객체를 OrderDto 객체로 변환")
  @Test
  void testOrderToOrderDto() {

    // Given
    Customer customer = Customer.builder()
        .name("홍길동")
        .build();
    Address billingAddress = Address.builder()
        .street("부산광역시 해운대구 해운대로 789")
        .city("부산")
        .build();
    Address shippingAddress = Address.builder()
        .street("서울특별시 강남구 테헤란로 456")
        .city("서울")
        .build();
    Order order = Order.builder()
        .customer(customer)
        .billingAddress(billingAddress)
        .shippingAddress(shippingAddress)
        .build();

    // When
    OrderDto orderDto = OrderMapper.MAPPER.orderToOrderDto(order);
    log.debug("orderDto: {}", orderDto);

    // Then
    assertAll(
        () -> assertEquals(customer.getName(), orderDto.getCustomerName()),
        () -> assertEquals(shippingAddress.getStreet(), orderDto.getShippingStreetAddress()),
        () -> assertEquals(shippingAddress.getCity(), orderDto.getShippingCity()),
        () -> assertEquals(billingAddress.getStreet(), orderDto.getBillingStreetAddress()),
        () -> assertEquals(billingAddress.getCity(), orderDto.getBillingCity())
    );
  }
}
