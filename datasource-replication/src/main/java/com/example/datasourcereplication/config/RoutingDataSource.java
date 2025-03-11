package com.example.datasourcereplication.config;

import com.example.datasourcereplication.common.type.DataSourceType;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * 읽기/쓰기 여부에 따라 DataSource(MASTER/SLAVE)를 동적으로 선택하는 클래스
 */
public class RoutingDataSource extends AbstractRoutingDataSource {

  /**
   * 트랜잭션이 읽기 전용인지 확인하여 MASTER 또는 SLAVE DataSource를 반환
   *
   * @return DataSourceType.SLAVE (읽기 전용) 또는 DataSourceType.MASTER (쓰기 가능)
   */
  @Override
  protected Object determineCurrentLookupKey() {
    return TransactionSynchronizationManager.isCurrentTransactionReadOnly()
        ? DataSourceType.SLAVE : DataSourceType.MASTER;
  }
}
