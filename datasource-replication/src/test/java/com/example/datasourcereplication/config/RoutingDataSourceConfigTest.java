package com.example.datasourcereplication.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.datasourcereplication.common.type.DataSourceType;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
class RoutingDataSourceConfigTest {

  @Autowired
  @Qualifier("routingDataSource")
  private DataSource routingDataSource;

  @Transactional
  @DisplayName("MasterDataSource Replication 설정 테스트")
  @Test
  void testMasterDataSourceReplication() {

    // Given
    RoutingDataSource realRoutingDataSource = (RoutingDataSource) routingDataSource;

    // When
    Object currentLookupKey = realRoutingDataSource.determineCurrentLookupKey();

    // Then
    log.info("Current DataSource Key: {}", currentLookupKey);
    assertEquals(DataSourceType.MASTER, currentLookupKey);
  }

  @Transactional(readOnly = true)
  @DisplayName("SlaveDataSource Replication 설정 테스트")
  @Test
  void testSlaveDataSourceReplication() {

    // Given
    RoutingDataSource realRoutingDataSource = (RoutingDataSource) routingDataSource;

    // When
    Object currentLookupKey = realRoutingDataSource.determineCurrentLookupKey();

    // Then
    log.info("Current DataSource Key : [{}]", currentLookupKey);
    assertEquals(DataSourceType.SLAVE, currentLookupKey);
  }
}
