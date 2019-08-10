package com.dts.manager.dao;

import com.dts.manager.model.DataSourceConfig;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DataSourceCheckRepository extends ReactiveCrudRepository<DataSourceConfig, String> {
    Mono<DataSourceConfig> findByUsername(String name);
    Flux<DataSourceConfig> findAllByType(String dbType);
}
