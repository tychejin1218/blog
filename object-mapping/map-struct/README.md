# [Java] MapStruct를 활용한 객체 변환

`org.mapstruct` 라이브러리는 Java Bean 매핑을 간편하게 수행할 수 있도록 도와주는 애너테이션 기반의 코드 생성 라이브러리입니다. 이번 포스트에서는 **Flattening**과 **Projection**을 통해 `MapStruct`의 사용법을 알아보겠습니다.

## 1. 라이브러리 추가

**Gradle 설정**

```groovy
dependencies {

    // Lombok - MapStruct보다 먼저 정의
    compileOnly 'org.projectlombok:lombok:1.18.34'
    annotationProcessor 'org.projectlombok:lombok:1.18.34'
    testCompileOnly 'org.projectlombok:lombok:1.18.34'
    testAnnotationProcessor 'org.projectlombok:lombok:1.18.34'

    // MapStruct
    implementation 'org.mapstruct:mapstruct:1.6.2'
    annotationProcessor 'org.mapstruct:mapstruct-processor:1.6.2'
}
```

### 주의사항: MapStruct 라이브러리보다 Lombok 라이브러리를 먼저 정의

`Lombok`을 `MapStruct`보다 먼저 정의해야 하는 이유는 두 라이브러리의 어노테이션 프로세서가 상호작용하는 방식 때문입니다:

1. **Lombok의 어노테이션 프로세싱**: `Lombok`은 소스 코드를 컴파일할 때, `@Getter`, `@Setter`, `@Builder`와 같은 어노테이션을 기반으로 추가적인 메서드와 생성자를 생성합니다. 이 과정은 컴파일의 초기 단계에서 이루어집니다.

2. **MapStruct의 어노테이션 프로세싱**: `MapStruct`는 매핑을 위한 메서드를 생성할 때, 소스 코드에 이미 존재하는 필드와 메서드를 기반으로 합니다. 따라서, `MapStruct`가 매핑 메서드를 생성할 때, 해당 클래스가 완전히 정의되어 있어야 합니다.

3. **프로세서 간 순서**: 만약 `MapStruct`가 `Lombok`의 결과물이 완료되기 전에 실행된다면, `MapStruct`는 아직 생성되지 않은 메서드나 필드를 참조하게 되어 오류가 발생할 수 있습니다. 따라서 `Lombok`이 먼저 실행되어 필요한 메서드와 필드를 모두 생성한 다음, `MapStruct`가 이를 기반으로 매핑 메서드를 생성할 수 있게 해야 합니다.

## 2. MapStruct Mapper Interface

Mapper 인터페이스는 `MapStruct`에서 매핑을 정의하는 곳입니다. 애너테이션 기반으로 매핑 규칙을 설정할 수 있습니다.

#### OrderMapper.java

```java
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
```

## 3. 자동 생성된 클래스

MapStruct는 컴파일 단계에서 매핑 코드를 생성하므로, 개발자가 직접 매핑 로직을 작성할 필요가 없습니다. 이렇게 자동으로 생성되는 클래스는 `Mappers.getMapper(Class<T> clazz)` 메서드를 통해 접근할 수 있습니다. 예를 들어, `OrderMapper` 인터페이스에 정의된 `orderToOrderDto`와 `orderDtoToOrder` 메서드는 컴파일 시 MapStruct에 의해 구현체가 생성됩니다.

생성된 클래스는 **target/generated-sources/annotations**와 같은 디렉토리에 위치합니다. 자동으로 생성된 클래스의 이름은 보통 인터페이스 이름 뒤에 `Impl`이 붙습니다. 예를 들어, `OrderMapper`의 구현체는 `OrderMapperImpl.java`가 됩니다.

### 자동 생성된 코드 예제 (OrderMapperImpl.java):

```java
package mapstruct.mapper;

import javax.annotation.processing.Generated;
import mapstruct.dto.OrderDto;
import mapstruct.entity.Address;
import mapstruct.entity.Customer;
import mapstruct.entity.Order;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-18T16:03:32+0900",
    comments = "version: 1.6.2, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.2 (Oracle Corporation)"
)
public class OrderMapperImpl implements OrderMapper {

  @Override
  public OrderDto orderToOrderDto(Order order) {
    if ( order == null ) {
      return null;
    }

    OrderDto.OrderDtoBuilder orderDto = OrderDto.builder();

    orderDto.customerName( orderCustomerName( order ) );
    orderDto.shippingStreetAddress( orderShippingAddressStreet( order ) );
    orderDto.shippingCity( orderShippingAddressCity( order ) );
    orderDto.billingStreetAddress( orderBillingAddressStreet( order ) );
    orderDto.billingCity( orderBillingAddressCity( order ) );

    return orderDto.build();
  }

  @Override
  public Order orderDtoToOrder(OrderDto orderDto) {
    if ( orderDto == null ) {
      return null;
    }

    Order.OrderBuilder order = Order.builder();

    order.customer( orderDtoToCustomer( orderDto ) );
    order.shippingAddress( orderDtoToAddress( orderDto ) );
    order.billingAddress( orderDtoToAddress1( orderDto ) );

    return order.build();
  }

  // 중략
}
```

## 4. 클래스 정의

#### OrderDto.java

```java
package mapstruct.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private String customerName;
    private String shippingStreetAddress;
    private String shippingCity;
    private String billingStreetAddress;
    private String billingCity;
}
```

#### Order.java

```java
package mapstruct.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Customer customer;
    private Address billingAddress;
    private Address shippingAddress;
}
```

#### Customer.java

```java
package mapstruct.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private String name;
}
```

#### Address.java

```java
package mapstruct.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String street;
    private String city;
}
```

### 5. Flattening 예제 및 Projection 예제

복잡한 객체 구조를 보다 단순한 구조로 변환하거나 여러 소스 객체의 필드를 결합하여 새로운 객체로 변환하는 예제 및 단위 테스트입니다.

#### Flattening 예제

복잡한 객체 `Order`를 단순한 객체 `OrderDto`로 변환하는 예제입니다.

```java
@DisplayName("Order 객체를 OrderDTO 객체로 변환")
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
    // Order 객체를 OrderDto로 변환
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
```

#### Projection 예제

`OrderDto` 객체의 필드를 사용하여 `Order` 객체를 생성하는 예제입니다.

```java
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
    // OrderDTO를 Order로 변환
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
```

---

## 참고
- MapStruct: [Official Documentation](https://mapstruct.org/documentation/stable/reference/html/)
- GitHub Repository: [MapStruct Repository](https://github.com/mapstruct/mapstruct)
