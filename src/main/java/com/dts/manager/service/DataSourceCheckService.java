package com.dts.manager.service;

import com.dts.manager.model.DataSourceConfig;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DataSourceCheckService {
    /**
     * query the cached database address based on the name of the database
     *
     * @param username
     * @return
     * @author adao
     */
    Mono<DataSourceConfig> findBySchemaName(String username);

    /**
     * query cached data information list
     *
     * @param dbType
     * @return
     * @author adao
     */
    Flux<DataSourceConfig> listDataSources(String dbType);

    /**
     * check if the database connection is normal.
     * <p>if the connection is successful, return true, the connection fails, and return false.
     *
     * @param dataSourceConfig
     * @return
     */
    Mono<Boolean> checkDataSourceConn(DataSourceConfig dataSourceConfig);
}
