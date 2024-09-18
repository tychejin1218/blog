package mapstruct.mapper;

import mapstruct.dto.OrderDto;
import mapstruct.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

  OrderMapper MAPPER = Mappers.getMapper(OrderMapper.class);

  @Mapping(source = "customer.name", target = "customerName")
  @Mapping(source = "shippingAddress.street", target = "shippingStreetAddress")
  @Mapping(source = "shippingAddress.city", target = "shippingCity")
  @Mapping(source = "billingAddress.street", target = "billingStreetAddress")
  @Mapping(source = "billingAddress.city", target = "billingCity")
  OrderDto orderToOrderDto(Order order);

  @Mapping(source = "customerName", target = "customer.name")
  @Mapping(source = "shippingStreetAddress", target = "shippingAddress.street")
  @Mapping(source = "shippingCity", target = "shippingAddress.city")
  @Mapping(source = "billingStreetAddress", target = "billingAddress.street")
  @Mapping(source = "billingCity", target = "billingAddress.city")
  Order orderDtoToOrder(OrderDto orderDto);
}
