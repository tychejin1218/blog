package com.example.datasourcereplication.config;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 다중 데이터 소스 설정 클래스
 * <p>master와 slave 데이터 소스를 정의</p>
 */
@Slf4j
@Configuration
public class DataSourceConfig {

  private final String MASTER_DATA_SOURCE = "masterDataSource"; // Master 데이터 소스의 Bean 이름
  private final String SLAVE_DATA_SOURCE = "slaveDataSource";   // Slave 데이터 소스의 Bean 이름

  /**
   * Primary (Master) 데이터 소스를 생성
   * <p>`spring.datasource.master`로 시작하는 설정 값을 사용</p>
   *
   * @return 설정된 Master 데이터 소스
   */
  @Primary
  @Bean(MASTER_DATA_SOURCE)
  @ConfigurationProperties(prefix = "spring.datasource.master")
  public DataSource masterDataSource() {
    return DataSourceBuilder
        .create()
        .type(HikariDataSource.class)
        .build();
  }

  /**
   * Slave 데이터 소스를 생성
   * <p>`spring.datasource.slave`로 시작하는 설정 값을 사용</p>
   *
   * @return 설정된 Slave 데이터 소스
   */
  @Bean(SLAVE_DATA_SOURCE)
  @ConfigurationProperties(prefix = "spring.datasource.slave")
  public DataSource slaveDataSource() {
    return DataSourceBuilder
        .create()
        .type(HikariDataSource.class)
        .build();
  }
}
