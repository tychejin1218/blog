package com.example.datasourcereplication.config;

import com.example.datasourcereplication.common.type.DataSourceEnum;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class RoutingDataSource extends AbstractRoutingDataSource {

  @Override
  protected Object determineCurrentLookupKey() {
    return TransactionSynchronizationManager.isCurrentTransactionReadOnly()
        ? DataSourceEnum.SLAVE : DataSourceEnum.MASTER;
  }
}