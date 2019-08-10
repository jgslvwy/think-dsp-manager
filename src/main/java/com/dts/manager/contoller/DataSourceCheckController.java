package com.dts.manager.contoller;

import com.dts.manager.model.DataSourceConfig;
import com.dts.manager.service.DataSourceCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class DataSourceCheckController {
    @Autowired
    private DataSourceCheckService dscService;

    @PostMapping("v1/db/check")
    public Mono<Boolean> checkDataSourceConn(@RequestBody DataSourceConfig dataSource) {
        return dscService.checkDataSourceConn(dataSource);
    }

    @GetMapping("v1/db/sources")
    public Flux<DataSourceConfig> listDataSources(@RequestParam String dbType) {
        return dscService.listDataSources(dbType);
    }

    @GetMapping("v1/db/sources/{name}")
    public Mono<DataSourceConfig> getDataSource(@PathVariable String name) {
        return dscService.findBySchemaName(name);
    }

    @PostMapping("v1/db/sources/tables")
    public Mono<String> getTables(@RequestBody DataSourceConfig dataSource) {
        //TODO 通过数据库配置查询增量数据表格
        return null;
    }
}
