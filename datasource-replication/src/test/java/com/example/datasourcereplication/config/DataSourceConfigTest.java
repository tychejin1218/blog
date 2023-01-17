package com.example.datasourcereplication.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@Slf4j
@SpringBootTest
class DataSourceConfigTest {

  @Autowired
  private Environment environment;

  @DisplayName("MasterDataSource 설정 테스트")
  @Test
  void masterDataSourceTest(
      @Qualifier("masterDataSource") final DataSource masterDataSource) {

    // Given
    String driverClassName = environment.getProperty("spring.datasource.master.hikari.driver-class-name");
    String jdbcUrl = environment.getProperty("spring.datasource.master.hikari.jdbc-url");
    Boolean readOnly = Boolean.valueOf(environment.getProperty("spring.datasource.master.hikari.read-only"));
    String username = environment.getProperty("spring.datasource.master.hikari.username");

    // When
    try (HikariDataSource hikariDataSource = (HikariDataSource) masterDataSource) {

      // Then
      log.info("hikariDataSource : [{}]", hikariDataSource);
      assertEquals(hikariDataSource.getDriverClassName(), driverClassName);
      assertEquals(hikariDataSource.getJdbcUrl(), jdbcUrl);
      assertEquals(hikariDataSource.isReadOnly(), readOnly);
      assertEquals(hikariDataSource.getUsername(), username);
    }
  }

  @DisplayName("SlaveDataSource 설정 테스트")
  @Test
  void slaveDataSourceTest(
      @Qualifier("slaveDataSource") final DataSource slaveDataSource) {

    // Given
    String driverClassName = environment.getProperty("spring.datasource.slave.hikari.driver-class-name");
    String jdbcUrl = environment.getProperty("spring.datasource.slave.hikari.jdbc-url");
    Boolean readOnly = Boolean.valueOf(environment.getProperty("spring.datasource.slave.hikari.read-only"));
    String username = environment.getProperty("spring.datasource.slave.hikari.username");

    // When
    try (HikariDataSource hikariDataSource = (HikariDataSource) slaveDataSource) {

      // Then
      log.info("hikariDataSource : [{}]", hikariDataSource);
      assertEquals(hikariDataSource.getDriverClassName(), driverClassName);
      assertEquals(hikariDataSource.getJdbcUrl(), jdbcUrl);
      assertEquals(hikariDataSource.isReadOnly(), readOnly);
      assertEquals(hikariDataSource.getUsername(), username);
    }
  }
}