package com.dts.manager.service.impl;

import com.dts.manager.dao.DataSourceCheckRepository;
import com.dts.manager.model.DataSourceConfig;
import com.dts.manager.service.DataSourceCheckService;
import com.dts.manager.util.JdbcUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DataSourceCheckServiceImpl implements DataSourceCheckService {

  @Autowired
  private DataSourceCheckRepository dataSourceCheckRepository;

  @Override
  public Mono<DataSourceConfig> findBySchemaName(String username) {
    return dataSourceCheckRepository.findByUsername(username);
  }

  @Override
  public Flux<DataSourceConfig> listDataSources(String dbType) {
    return dataSourceCheckRepository.findAllByType(dbType);
  }

  @Override
  public Mono<Boolean> checkDataSourceConn(DataSourceConfig dataSourceConfig) {
    Boolean connSuccess = JdbcUtils.isConnSuccess(dataSourceConfig);
    if (connSuccess) {
      dataSourceCheckRepository.save(dataSourceConfig);
    }
    return Mono.just(connSuccess);
  }
}
