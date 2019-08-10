package com.dts.manager.model;

import com.dts.core.common.contants.DbType;
import com.dts.core.common.contants.RunMode;
import com.dts.manager.common.RunState;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class DataTask {
    @Id
    private String taskId;
    private RunMode runMode;
    private RunState runState;
    private List<String> tables;
    private DataSourceConfig sourceConfig;
    private DataSourceConfig targetConfig;

}

