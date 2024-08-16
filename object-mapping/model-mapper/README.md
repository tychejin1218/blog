# [Java] ModelMapper를 활용한 객체 변환

`org.modelmapper` 라이브러리는 복잡한 객체를 단순화하거나, 단순한 객체를 복잡하게 변환하는데 유용합니다. 이번 포스트에서는 두 가지 주요 변환 방식인 **Flattening**과 **Projection**을 통해 ModelMapper의 사용법을 알아보겠습니다.

## 1. 라이브러리 추가

**Gradle 설정**

```groovy
dependencies {
    // ModelMapper
    implementation 'org.modelmapper:modelmapper:3.2.1'
}
```

Maven Repository: [ModelMapper](https://mvnrepository.com/artifact/org.modelmapper/modelmapper)

## 2. Flattening 예제

Flattening은 복잡한 객체 구조를 보다 단순한 구조로 변환하는 것을 의미합니다. 예제 코드를 통해 이를 살펴보겠습니다.

### 2.1 기본적인 Flattening

복잡한 객체 `Order`를 단순한 객체 `OrderDTO`로 변환하는 예제입니다.

```java
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
```

이 예제에서는 `Order` 객체를 `OrderDTO`로 변환하여 평평한 구조를 가집니다. `OrderDTO`는 `Order`의 일부 필드만을 포함하여 좀 더 단순화된 형태입니다.

### 2.2 PropertyMap 사용

PropertyMap을 사용하여 명시적으로 매핑을 설정하는 예제입니다.

```java
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
```

이 예제에서는 `PropertyMap`을 사용하여 `Person` 객체를 `PersonDTO` 객체로 명시적으로 매핑하는 방법을 보여줍니다.

## 3. Projection 예제

Projection은 여러 소스 객체의 필드를 결합하여 새로운 객체를 생성하는 것을 의미합니다. 예제 코드를 통해 이를 살펴보겠습니다.

### 3.1 기본적인 Projection

단순한 객체 `OrderInfo`를 복잡한 객체 `Order`로 변환하는 예제입니다.

```java
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
```

이 예제에서는 단순한 구조의 `OrderInfo` 객체를 복잡한 구조의 `Order` 객체로 변환하는 방법을 보여줍니다.

### 3.2 PropertyMap 사용

PropertyMap을 사용하여 명시적으로 매핑을 설정하는 예제입니다.

```java
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
```

이 예제에서는 `PropertyMap`을 사용하여 `OrderDTO` 객체를 명시적으로 매핑하는 방법을 보여줍니다.

---

ModelMapper: [Github Repository 링크](https://github.com/modelmapper/modelmapper)
