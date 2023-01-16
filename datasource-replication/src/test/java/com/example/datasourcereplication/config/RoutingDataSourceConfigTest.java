package com.example.datasourcereplication.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
class RoutingDataSourceConfigTest {

  private static final String DETERMINE_CURRENT_LOOKUP_KEY = "determineCurrentLookupKey";

  @Transactional(readOnly = false)
  @DisplayName("MasterDataSource Replication 설정 테스트")
  @Test
  void testMasterDataSourceReplication() throws Exception {

    // Given
    RoutingDataSource routingDataSource = new RoutingDataSource();

    // When
    Method declaredMethod = RoutingDataSource.class.getDeclaredMethod(DETERMINE_CURRENT_LOOKUP_KEY);
    declaredMethod.setAccessible(true);

    Object object = declaredMethod.invoke(routingDataSource);

    // Then
    log.info("object : [{}]", object);
    assertEquals("master", object.toString());
  }

  @Transactional(readOnly = true)
  @DisplayName("SlaveDataSource Replication 설정 테스트")
  @Test
  void testSlaveDataSourceReplication() throws Exception {

    // Given
    RoutingDataSource routingDataSource = new RoutingDataSource();

    // When
    Method declaredMethod = RoutingDataSource.class.getDeclaredMethod(DETERMINE_CURRENT_LOOKUP_KEY);
    declaredMethod.setAccessible(true);

    Object object = declaredMethod.invoke(routingDataSource);

    // Then
    log.info("object : [{}]", object);
    assertEquals("slave", object.toString());
  }
}