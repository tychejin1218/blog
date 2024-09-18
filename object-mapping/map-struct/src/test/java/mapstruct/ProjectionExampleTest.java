package mapstruct;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import lombok.extern.slf4j.Slf4j;
import mapstruct.dto.OrderDto;
import mapstruct.entity.Order;
import mapstruct.mapper.OrderMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class ProjectionExampleTest {

  @DisplayName("OrderDto를 Order로 매핑")
  @Test
  void testOrderDtoToOrderMapping() {

    // Given
    OrderDto orderDto = OrderDto.builder()
        .customerName("홍길동")
        .shippingStreetAddress("서울특별시 강남구 테헤란로 456")
        .shippingCity("서울")
        .billingStreetAddress("부산광역시 해운대구 해운대로 789")
        .billingCity("부산")
        .build();

    // When
    Order order = OrderMapper.MAPPER.orderDtoToOrder(orderDto);
    log.debug("order: {}", order);

    // Then
    assertAll(
        () -> assertEquals(orderDto.getCustomerName(), order.getCustomer().getName()),
        () -> assertEquals(orderDto.getShippingStreetAddress(), order.getShippingAddress().getStreet()),
        () -> assertEquals(orderDto.getShippingCity(), order.getShippingAddress().getCity()),
        () -> assertEquals(orderDto.getBillingStreetAddress(), order.getBillingAddress().getStreet()),
        () -> assertEquals(orderDto.getBillingCity(), order.getBillingAddress().getCity())
    );
  }
}
