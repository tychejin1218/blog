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
  void testMasterDataSource(
      @Qualifier("masterDataSource") final DataSource masterDataSource) {

    validateDataSource(
        masterDataSource,
        "spring.datasource.master.driver-class-name",
        "spring.datasource.master.jdbc-url",
        "spring.datasource.master.read-only",
        "spring.datasource.master.username"
    );
  }

  @DisplayName("SlaveDataSource 설정 테스트")
  @Test
  void testSlaveDataSource(
      @Qualifier("slaveDataSource") final DataSource slaveDataSource) {

    validateDataSource(
        slaveDataSource,
        "spring.datasource.slave.driver-class-name",
        "spring.datasource.slave.jdbc-url",
        "spring.datasource.slave.read-only",
        "spring.datasource.slave.username"
    );
  }

  /**
   * 데이터 소스 설정값 검증
   *
   * @param dataSource          데이터 소스 객체
   * @param driverClassNameProp Driver Class Name에 대한 프로퍼티 키
   * @param jdbcUrlProp         JDBC URL에 대한 프로퍼티 키
   * @param readOnlyProp        Read-only 여부에 대한 프로퍼티 키
   * @param usernameProp        데이터베이스 Username에 대한 프로퍼티 키
   */
  private void validateDataSource(
      DataSource dataSource,
      String driverClassNameProp,
      String jdbcUrlProp,
      String readOnlyProp,
      String usernameProp) {

    // Given: Environment에서 설정 값 조회
    String driverClassName = environment.getProperty(driverClassNameProp);
    String jdbcUrl = environment.getProperty(jdbcUrlProp);
    Boolean readOnly = Boolean.valueOf(environment.getProperty(readOnlyProp));
    String username = environment.getProperty(usernameProp);

    // When: DataSource에서 실제 값 조회
    try (HikariDataSource hikariDataSource = (HikariDataSource) dataSource) {

      // Then: 설정 값과 실제 값 비교
      assertEquals(driverClassName, hikariDataSource.getDriverClassName());
      assertEquals(jdbcUrl, hikariDataSource.getJdbcUrl());
      assertEquals(readOnly, hikariDataSource.isReadOnly());
      assertEquals(username, hikariDataSource.getUsername());
    }
  }
}
