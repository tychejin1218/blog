package com.example.datasourcereplication.config;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NameTokenizers;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * 데이터 소스와 관련된 설정을 구성하는 클래스
 * <p>읽기/쓰기 분리를 위한 RoutingDataSource를 구성하며, JPA와 트랜잭션 관리를 위한 Bean을 등록</p>
 */
@Slf4j
@EnableJpaRepositories(
    basePackages = "com.example.datasourcereplication.domain.repository",
    entityManagerFactoryRef = "entityManagerFactory",
    transactionManagerRef = "transactionManager"
)
@Configuration
public class DataSourceConfig {

  private static final String MASTER_DATA_SOURCE = "masterDataSource";
  private static final String SLAVE_DATA_SOURCE = "slaveDataSource";
  private static final String DATA_SOURCE = "dataSource";
  private static final String ENTITY_PACKAGE = "com.example.datasourcereplication.domain.entity";
  private static final String ENTITY_MANAGER_FACTORY = "entityManagerFactory";
  private static final String TRANSACTION_MANAGER = "transactionManager";

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

  /**
   * 읽기/쓰기 분리를 위해 DataSource를 구성
   *
   * @param masterDataSource Master 데이터 소스
   * @param slaveDataSource  Slave 데이터 소스
   * @return Proxy가 적용된 데이터 소스
   */
  @Bean(DATA_SOURCE)
  public DataSource dataSource(
      @Qualifier(MASTER_DATA_SOURCE) final DataSource masterDataSource,
      @Qualifier(SLAVE_DATA_SOURCE) final DataSource slaveDataSource) {
    LazyConnectionDataSourceProxy lazyConnectionDataSourceProxy =
        new LazyConnectionDataSourceProxy(masterDataSource);
    lazyConnectionDataSourceProxy.setReadOnlyDataSource(slaveDataSource);
    return lazyConnectionDataSourceProxy;
  }

  /**
   * JPA EntityManagerFactory를 구성
   *
   * @param dataSource 데이터 소스 @return
   * @return {@link LocalContainerEntityManagerFactoryBean} 타입의 EntityManagerFactory Bean
   */
  @Bean(ENTITY_MANAGER_FACTORY)
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(
      @Qualifier(DATA_SOURCE) DataSource dataSource) {
    LocalContainerEntityManagerFactoryBean entityManagerFactory
        = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactory.setDataSource(dataSource);
    entityManagerFactory.setPackagesToScan(ENTITY_PACKAGE);
    entityManagerFactory.setJpaVendorAdapter(this.jpaVendorAdapter());
    entityManagerFactory.setPersistenceUnitName("entityManager");
    return entityManagerFactory;
  }

  /**
   * Hibernate를 사용하는 JPA Vendor Adapter를 생성
   *
   * @return {@link JpaVendorAdapter} 타입의 HibernateJpaVendorAdapter Bean
   */
  private JpaVendorAdapter jpaVendorAdapter() {
    HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
    hibernateJpaVendorAdapter.setGenerateDdl(false);
    hibernateJpaVendorAdapter.setShowSql(false);
    hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL8Dialect");
    return hibernateJpaVendorAdapter;
  }

  /**
   * JPA 트랜잭션 관리자를 설정
   *
   * @param entityManagerFactory EntityManagerFactory Bean
   * @return {@link PlatformTransactionManager} 타입의 JpaTransactionManager Bean
   */
  @Bean(TRANSACTION_MANAGER)
  public PlatformTransactionManager platformTransactionManager(
      @Qualifier("entityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
    JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
    jpaTransactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
    return jpaTransactionManager;
  }

  /**
   * ModelMapper 빈을 설정
   *
   * @return {@link ModelMapper} 객체
   */
  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STRICT)
        .setDestinationNameTokenizer(NameTokenizers.CAMEL_CASE)
        .setSourceNameTokenizer(NameTokenizers.CAMEL_CASE)
        .setFieldMatchingEnabled(true)
        .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
    return modelMapper;
  }
}
